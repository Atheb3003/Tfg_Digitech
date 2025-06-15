package com.gestion.application.service.transactiondetail.impl;

import com.gestion.application.dto.TypeBreakdownResponse;
import com.gestion.application.model.TransactionDetail;
import com.gestion.application.repository.TransactionDetailRepository;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTypeBreakdownImpl {

  private final TransactionDetailRepository transactionDetailRepository;

  public TypeBreakdownResponse getTypeBreakdown(LocalDate startDate, LocalDate endDate) {
    // Si las fechas son nulas, consulta todo el rango disponible
    if (startDate == null || endDate == null) {
      List<TransactionDetail> visibles = transactionDetailRepository.findAllByIsVisibleTrue();
      if (visibles.isEmpty()) {
        startDate = LocalDate.now();
        endDate = LocalDate.now();
      } else {
        if (startDate == null)
          startDate =
              visibles.stream()
                  .map(td -> td.getTransaction().getTransactionDate())
                  .min(LocalDate::compareTo)
                  .orElse(LocalDate.now());
        if (endDate == null)
          endDate =
              visibles.stream()
                  .map(td -> td.getTransaction().getTransactionDate())
                  .max(LocalDate::compareTo)
                  .orElse(LocalDate.now());
      }
    }

    List<TransactionDetail> details =
        transactionDetailRepository.findAllByIsVisibleAndTransactionDateBetween(startDate, endDate);

    Map<String, TypeAccumulator> map = new HashMap<>();
    for (TransactionDetail td : details) {
      String type = null;
      if (td.getProduct() != null) type = "PRODUCT";
      else if (td.getProtocolTreatment() != null) type = "PROTOCOL_TREATMENT";
      else if (td.getSurgeryReservation() != null) type = "SURGERY";
      if (type == null) continue;

      int quantity = td.getQuantity() != null ? td.getQuantity() : 1;
      BigDecimal amount = td.getPrice() != null ? td.getPrice() : BigDecimal.ZERO;
      TypeAccumulator acc = map.getOrDefault(type, new TypeAccumulator());
      acc.count += quantity;
      acc.total = acc.total.add(amount.multiply(BigDecimal.valueOf(quantity)));
      map.put(type, acc);
    }

    // Formato europeo: 1.234,56
    DecimalFormat df =
        new DecimalFormat("#,##0.00", DecimalFormatSymbols.getInstance(Locale.GERMANY));

    List<TypeBreakdownResponse.TypeSummary> types = new ArrayList<>();
    for (Map.Entry<String, TypeAccumulator> entry : map.entrySet()) {
      TypeBreakdownResponse.TypeSummary summary = new TypeBreakdownResponse.TypeSummary();
      summary.setType(entry.getKey());
      summary.setQuantity(entry.getValue().count);
      summary.setTotalIncome(df.format(entry.getValue().total));
      types.add(summary);
    }

    TypeBreakdownResponse response = new TypeBreakdownResponse();
    response.setTypes(types);
    response.setStartDate(startDate.toString());
    response.setEndDate(endDate.toString());
    return response;
  }

  private static class TypeAccumulator {
    int count = 0;
    BigDecimal total = BigDecimal.ZERO;
  }
}
