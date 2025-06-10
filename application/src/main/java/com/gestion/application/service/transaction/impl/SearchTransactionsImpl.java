package com.gestion.application.service.transaction.impl;

import com.gestion.application.dto.TransactionResponse;
import com.gestion.application.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchTransactionsImpl {

  private final TransactionRepository transactionRepository;

  /**
   * Lógica de búsqueda de transacciones visibles, proyectando directamente a TransactionResponse.
   */
  public Page<TransactionResponse> searchVisibleTransactions(String search, Pageable pageable) {
    return transactionRepository.searchVisibleTransactions(search, pageable);
  }
}
