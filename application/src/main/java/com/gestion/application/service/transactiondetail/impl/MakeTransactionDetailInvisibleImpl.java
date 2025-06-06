package com.gestion.application.service.transactiondetail.impl;

import com.gestion.application.exception.TransactionDetailAlreadyInvisibleException;
import com.gestion.application.exception.TransactionDetailNotFoundException;
import com.gestion.application.model.TransactionDetail;
import com.gestion.application.repository.TransactionDetailRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MakeTransactionDetailInvisibleImpl {

  private final TransactionDetailRepository detailRepo;

  @Transactional
  public TransactionDetail setInvisible(Integer id) {
    TransactionDetail d =
        detailRepo.findById(id).orElseThrow(() -> new TransactionDetailNotFoundException(id));
    if (Boolean.FALSE.equals(d.getIsVisible())) {
      throw new TransactionDetailAlreadyInvisibleException(id);
    }
    d.setIsVisible(false);
    return detailRepo.save(d);
  }
}
