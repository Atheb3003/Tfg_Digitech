package com.gestion.application.service.transactiondetail.impl;

import com.gestion.application.dto.ProductBreakdownItemResponse;
import com.gestion.application.dto.ProductBreakdownResponse;
import com.gestion.application.model.TransactionDetail;
import com.gestion.application.repository.TransactionDetailRepository;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetProductBreakdownImpl {

  private final TransactionDetailRepository transactionDetailRepository;

  public ProductBreakdownResponse getProductBreakdown(LocalDate startDate, LocalDate endDate) {
    // Si fechas son nulas, pon valores extremos para incluir todo
    if (startDate == null) startDate = LocalDate.of(1970, 1, 1);
    if (endDate == null) endDate = LocalDate.now();

    List<TransactionDetail> details =
        transactionDetailRepository.findAllByIsVisibleAndTransactionDateBetween(startDate, endDate);

    // Mapa agrupado por (type+name)
    Map<String, ProductBreakdownItemResponse> map = new LinkedHashMap<>();

    for (TransactionDetail td : details) {
      String type = null;
      String name = null;
      double totalIncome = 0;
      int qty = td.getQuantity() != null ? td.getQuantity() : 1;

      if (td.getProduct() != null) {
        type = "PRODUCT";
        name = td.getProduct().getName();
      } else if (td.getProtocolTreatment() != null) {
        type = "PROTOCOL_TREATMENT";
        if (td.getProtocolTreatment().getProduct() != null) {
          name = td.getProtocolTreatment().getProduct().getName();
        } else {
          name = "Protocolo";
        }
      } else if (td.getSurgeryReservation() != null) {
        type = "SURGERY";
        name =
            td.getSurgeryReservation().getSurgicalTechnique()
                + " "
                + td.getSurgeryReservation().getFollicularUnits()
                + "UF";
      } else {
        continue; // Si no hay ninguno, ignora el registro
      }

      double income = td.getPrice() != null ? td.getPrice().doubleValue() * qty : 0.0;

      // Clave única por tipo + nombre
      String key = type + "|" + name;
      if (!map.containsKey(key)) {
        ProductBreakdownItemResponse item = new ProductBreakdownItemResponse();
        item.setType(type);
        item.setName(name);
        item.setQuantity(qty);
        item.setIncome(formatNumber(income));
        map.put(key, item);
      } else {
        ProductBreakdownItemResponse item = map.get(key);
        item.setQuantity(item.getQuantity() + qty);
        double prevIncome = parseNumber(item.getIncome());
        item.setIncome(formatNumber(prevIncome + income));
      }
    }

    // ORDENAR por cantidad descendente
    List<ProductBreakdownItemResponse> sortedItems = new ArrayList<>(map.values());
    sortedItems.sort((a, b) -> Integer.compare(b.getQuantity(), a.getQuantity()));

    ProductBreakdownResponse resp = new ProductBreakdownResponse();
    resp.setStartDate(startDate.toString());
    resp.setEndDate(endDate.toString());
    resp.setItems(sortedItems);
    return resp;
  }

  // Formato español: 1.234,56
  private String formatNumber(double n) {
    NumberFormat nf = NumberFormat.getInstance(new Locale("es", "ES"));
    nf.setMinimumFractionDigits(2);
    nf.setMaximumFractionDigits(2);
    return nf.format(n);
  }

  private double parseNumber(String n) {
    try {
      return NumberFormat.getInstance(new Locale("es", "ES")).parse(n).doubleValue();
    } catch (Exception e) {
      return 0.0;
    }
  }
}
