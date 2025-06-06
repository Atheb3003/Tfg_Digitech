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
public class GetAllTransactionsImpl {

  private final TransactionRepository txRepo;
  private final TransactionMapper mapper;

  /** Devuelve todas las transacciones */
  public List<TransactionResponse> getAllTransactions() {
    return txRepo.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
  }
}
