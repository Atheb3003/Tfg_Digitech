package com.gestion.application.service.transaction.impl;

import com.gestion.application.dto.TransactionRequest;
import com.gestion.application.dto.TransactionResponse;
import com.gestion.application.exception.ContactNotFoundException;
import com.gestion.application.exception.TransactionCreationException;
import com.gestion.application.exception.TransactionInvalidDataException;
import com.gestion.application.mapper.TransactionMapper;
import com.gestion.application.model.Transaction;
import com.gestion.application.repository.ContactRepository;
import com.gestion.application.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTransactionImpl {

  private final TransactionRepository txRepo;
  private final ContactRepository contactRepo;
  private final TransactionMapper mapper;

  public TransactionResponse create(TransactionRequest req) {
    // 1) validar campos
    if (req.getTransactionDate() == null
        || req.getAmount() == null
        || req.getAmount() < 0
        || req.getIdPatient() == null) {
      throw new TransactionInvalidDataException();
    }

    // 2) comprobar contacto
    contactRepo
        .findById(req.getIdPatient())
        .orElseThrow(() -> new ContactNotFoundException(req.getIdPatient()));

    // 3) mapear y guardar
    Transaction entity = mapper.toEntity(req);
    try {
      Transaction saved = txRepo.save(entity);
      return mapper.toResponse(saved);
    } catch (Exception ex) {
      throw new TransactionCreationException();
    }
  }
}
