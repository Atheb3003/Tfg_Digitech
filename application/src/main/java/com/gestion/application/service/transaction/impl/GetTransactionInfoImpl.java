// src/main/java/com/gestion/application/service/transaction/impl/GetTransactionInfoImpl.java
package com.gestion.application.service.transaction.impl;

import com.gestion.application.dto.TransactionResponse;
import com.gestion.application.model.Transaction;
import com.gestion.application.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTransactionInfoImpl {
  private final TransactionRepository transactionRepository;

  public TransactionResponse getTransactionInfo(Integer id) {
    Transaction tx =
        transactionRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("No existe la transacción con id " + id));

    TransactionResponse dto = new TransactionResponse();
    dto.setIdTransaction(tx.getIdTransaction());
    dto.setTransactionDate(tx.getTransactionDate());
    dto.setAmount(tx.getAmount());
    dto.setDescription(tx.getDescription());
    dto.setPaymentMethod(tx.getPaymentMethod());
    dto.setIsVisible(tx.getIsVisible());

    // Paciente relacionado
    if (tx.getPatient() != null) {
      dto.setIdPatient(tx.getPatient().getIdPatient());
      if (tx.getPatient().getContact() != null) {
        dto.setIdContactString(tx.getPatient().getContact().getIdContactString());
      }
    }

    return dto;
  }
}
