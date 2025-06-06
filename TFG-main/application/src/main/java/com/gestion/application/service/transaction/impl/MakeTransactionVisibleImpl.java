package com.gestion.application.service.transaction.impl;

import com.gestion.application.exception.TransactionAlreadyVisibleException;
import com.gestion.application.exception.TransactionNotFoundException;
import com.gestion.application.model.Transaction;
import com.gestion.application.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MakeTransactionVisibleImpl {

  private final TransactionRepository txRepo;

  /** Fija isVisible = true. → 404 si no existe → 409 si ya está visible */
  @Transactional
  public Transaction makeVisible(Integer id) {
    Transaction tx = txRepo.findById(id).orElseThrow(() -> new TransactionNotFoundException(id));
    if (Boolean.TRUE.equals(tx.getIsVisible())) {
      throw new TransactionAlreadyVisibleException(id);
    }
    tx.setIsVisible(true);
    return txRepo.save(tx);
  }
}
