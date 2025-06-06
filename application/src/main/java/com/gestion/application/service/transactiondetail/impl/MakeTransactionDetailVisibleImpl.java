package com.gestion.application.service.transactiondetail.impl;

import com.gestion.application.exception.TransactionDetailAlreadyVisibleException;
import com.gestion.application.exception.TransactionDetailNotFoundException;
import com.gestion.application.model.TransactionDetail;
import com.gestion.application.repository.TransactionDetailRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MakeTransactionDetailVisibleImpl {

  private final TransactionDetailRepository detailRepo;

  @Transactional
  public TransactionDetail setVisible(Integer id) {
    TransactionDetail d =
        detailRepo.findById(id).orElseThrow(() -> new TransactionDetailNotFoundException(id));
    if (Boolean.TRUE.equals(d.getIsVisible())) {
      throw new TransactionDetailAlreadyVisibleException(id);
    }
    d.setIsVisible(true);
    return detailRepo.save(d);
  }
}
