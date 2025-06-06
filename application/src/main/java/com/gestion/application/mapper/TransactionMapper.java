package com.gestion.application.mapper;

import com.gestion.application.dto.TransactionRequest;
import com.gestion.application.dto.TransactionResponse;
import com.gestion.application.model.Patient;
import com.gestion.application.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

  public Transaction toEntity(TransactionRequest dto) {
    Transaction tx = new Transaction();
    tx.setTransactionDate(dto.getTransactionDate());
    tx.setAmount(dto.getAmount());
    tx.setDescription(dto.getDescription());
    // asociamos paciente por su ID
    Patient p = new Patient();
    p.setIdPatient(dto.getIdPatient());
    tx.setPatient(p);
    tx.setIsVisible(true);
    return tx;
  }

  public TransactionResponse toResponse(Transaction entity) {
    TransactionResponse dto = new TransactionResponse();
    dto.setIdTransaction(entity.getIdTransaction());
    dto.setTransactionDate(entity.getTransactionDate());
    dto.setAmount(entity.getAmount());
    dto.setDescription(entity.getDescription());
    dto.setIsVisible(entity.getIsVisible());
    dto.setIdPatient(entity.getPatient().getIdPatient());
    return dto;
  }
}
