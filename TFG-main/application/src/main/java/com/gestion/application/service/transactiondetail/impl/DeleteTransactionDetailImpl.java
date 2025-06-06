package com.gestion.application.service.transactiondetail.impl;

import com.gestion.application.exception.TransactionDetailNotFoundException;
import com.gestion.application.repository.TransactionDetailRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteTransactionDetailImpl {

  private final TransactionDetailRepository detailRepo;

  @Transactional
  public void deleteDetail(Integer id) {
    if (!detailRepo.existsById(id)) {
      throw new TransactionDetailNotFoundException(id);
    }
    detailRepo.deleteById(id);
  }
}
