package com.gestion.application.service.transactiondetail.impl;

import com.gestion.application.dto.TransactionDetailResponse;
import com.gestion.application.mapper.TransactionDetailMapper;
import com.gestion.application.repository.TransactionDetailRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllTransactionDetailsImpl {

  private final TransactionDetailRepository detailRepo;
  private final TransactionDetailMapper mapper;

  public List<TransactionDetailResponse> getAll() {
    return detailRepo.findAll()
            .stream()
            .map(mapper::toResponse)
            .collect(Collectors.toList());
  }
}
