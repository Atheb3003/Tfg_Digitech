package com.gestion.application.service.transaction.impl;

import com.gestion.application.dto.TransactionResponse;
import com.gestion.application.mapper.TransactionMapper;
import com.gestion.application.repository.TransactionRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetVisibleTransactionsImpl {

  private final TransactionRepository txRepo;
  private final TransactionMapper mapper;

  /** Recupera todas las transacciones con isVisible = true. */
  public List<TransactionResponse> getVisibleTransactions() {
    return txRepo.findAllByIsVisibleTrue().stream()
        .map(mapper::toResponse)
        .collect(Collectors.toList());
  }
}
