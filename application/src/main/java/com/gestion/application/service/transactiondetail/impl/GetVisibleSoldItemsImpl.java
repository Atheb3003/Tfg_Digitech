package com.gestion.application.service.transactiondetail.impl;

import com.gestion.application.dto.SoldItemResponse;
import com.gestion.application.model.TransactionDetail;
import com.gestion.application.repository.TransactionDetailRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetVisibleSoldItemsImpl {

  private final TransactionDetailRepository transactionDetailRepository;

  public Page<SoldItemResponse> getVisibleSoldItems(Pageable pageable) {
    // 1. Traer TODOS los detalles visibles SIN paginar en BD
    List<TransactionDetail> allVisible = transactionDetailRepository.findAllByIsVisibleTrue();

    // 2. Expandir la lista
    List<SoldItemResponse> result = new ArrayList<>();
    for (TransactionDetail td : allVisible) {
      int quantity = td.getQuantity() != null ? td.getQuantity() : 1;
      for (int i = 0; i < quantity; i++) {
        SoldItemResponse dto = new SoldItemResponse();
        // Mapeo del tipo
        if (td.getProduct() != null) dto.setType("PRODUCT");
        else if (td.getProtocolTreatment() != null) dto.setType("PROTOCOL_TREATMENT");
        else if (td.getSurgeryReservation() != null) dto.setType("SURGERY");

        if ("PRODUCT".equals(dto.getType())) {
          dto.setName(td.getProduct().getName());
        } else if ("PROTOCOL_TREATMENT".equals(dto.getType())) {
          dto.setName(td.getProtocolTreatment().getProduct().getName());
        } else if ("SURGERY".equals(dto.getType())) {
          dto.setName(
              td.getSurgeryReservation().getSurgicalTechnique()
                  + " "
                  + td.getSurgeryReservation().getFollicularUnits()
                  + "UF");
        }
        dto.setTransactionId(td.getTransaction().getIdTransaction());
        dto.setPaymentMethod(td.getTransaction().getPaymentMethod());
        dto.setAmount(td.getPrice() != null ? td.getPrice().doubleValue() : 0.0);

        if (td.getTransaction() != null
            && td.getTransaction().getPatient() != null
            && td.getTransaction().getPatient().getContact() != null) {
          dto.setIdContactString(
              td.getTransaction().getPatient().getContact().getIdContactString());
        }

        dto.setTransactionDate(td.getTransaction().getTransactionDate());
        result.add(dto);
      }
    }

    // ORDENAR POR FECHA DESCENDENTE (más recientes primero)
    result.sort((a, b) -> b.getTransactionDate().compareTo(a.getTransactionDate()));

    // 3. Paginar sobre la lista expandida
    int start = (int) pageable.getOffset();
    int end = Math.min(start + pageable.getPageSize(), result.size());
    List<SoldItemResponse> pageContent =
        start > end ? new ArrayList<>() : result.subList(start, end);

    return new PageImpl<>(pageContent, pageable, result.size());
  }
}
