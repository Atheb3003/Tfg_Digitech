package com.gestion.application.service.transactiondetail.impl;

import com.gestion.application.dto.TransactionDetailResponse;
import com.gestion.application.exception.TransactionNotFoundException;
import com.gestion.application.mapper.TransactionDetailMapper;
import com.gestion.application.repository.TransactionDetailRepository;
import com.gestion.application.repository.TransactionRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTransactionDetailsByTransactionImpl {

  private final TransactionDetailRepository detailRepo;
  private final TransactionRepository txRepo;
  private final TransactionDetailMapper mapper;

  public List<TransactionDetailResponse> getByTransaction(Integer txId) {
    txRepo.findById(txId).orElseThrow(() -> new TransactionNotFoundException(txId));
    return detailRepo.findByTransaction_IdTransaction(txId).stream()
        .map(mapper::toResponse)
        .collect(Collectors.toList());
  }
}
