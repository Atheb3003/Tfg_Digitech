package com.gestion.application.service.transaction.impl;

import com.gestion.application.exception.TransactionNotFoundException;
import com.gestion.application.repository.TransactionDetailRepository;
import com.gestion.application.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteTransactionImpl {

  private final TransactionRepository txRepo;
  private final TransactionDetailRepository detailRepo;

  /**
   * Borra primero todos los detalles de la transacción {id} y luego la transacción en sí.
   *
   * <p>→ 404 si la transacción no existe.
   */
  @Transactional
  public void deleteTransaction(Integer id) {
    if (!txRepo.existsById(id)) {
      throw new TransactionNotFoundException(id);
    }
    detailRepo.deleteByTransaction_IdTransaction(id);
    txRepo.deleteById(id);
  }
}
