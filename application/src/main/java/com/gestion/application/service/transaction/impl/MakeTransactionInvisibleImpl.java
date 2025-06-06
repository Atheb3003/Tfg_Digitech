package com.gestion.application.service.transaction.impl;

import com.gestion.application.exception.TransactionAlreadyInvisibleException;
import com.gestion.application.exception.TransactionNotFoundException;
import com.gestion.application.model.Transaction;
import com.gestion.application.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MakeTransactionInvisibleImpl {

  private final TransactionRepository txRepo;

  /** Fija isVisible = false. → 404 si no existe → 409 si ya está invisible */
  @Transactional
  public Transaction makeInvisible(Integer id) {
    Transaction tx = txRepo.findById(id).orElseThrow(() -> new TransactionNotFoundException(id));
    if (Boolean.FALSE.equals(tx.getIsVisible())) {
      throw new TransactionAlreadyInvisibleException(id);
    }
    tx.setIsVisible(false);
    return txRepo.save(tx);
  }
}
