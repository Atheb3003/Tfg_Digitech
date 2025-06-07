package com.gestion.application.service.transaction.impl;

import com.gestion.application.dto.TransactionResponse;
import com.gestion.application.mapper.TransactionMapper;
import com.gestion.application.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetVisibleTransactionsImpl {

  private final TransactionRepository txRepo;
  private final TransactionMapper mapper;


  public Page<TransactionResponse> getVisibleTransactions(Pageable pageable) {
    return txRepo.findAllByIsVisibleTrue(pageable)
            .map(mapper::toResponse);
  }

}
