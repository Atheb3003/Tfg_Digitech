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
    tx.setPaymentMethod(dto.getPaymentMethod()); // ← Se asigna el enum PaymentMethod
    // El servicio sobrescribe el paciente con la entidad recoverada de la BD,
    // pero aquí seteamos un Patient “de relleno” para evitar nullPointer.
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
    dto.setPaymentMethod(entity.getPaymentMethod()); // ← Se devuelve el enum PaymentMethod
    dto.setIsVisible(entity.getIsVisible());
    dto.setIdPatient(entity.getPatient().getIdPatient());
    return dto;
  }
}
