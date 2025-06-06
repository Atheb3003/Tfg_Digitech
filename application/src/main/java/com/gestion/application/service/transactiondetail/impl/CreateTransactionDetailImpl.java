package com.gestion.application.service.transactiondetail.impl;

import com.gestion.application.dto.TransactionDetailRequest;
import com.gestion.application.dto.TransactionDetailResponse;
import com.gestion.application.exception.ProductNotFoundException;
import com.gestion.application.exception.TransactionDetailCreationException;
import com.gestion.application.exception.TransactionDetailInvalidDataException;
import com.gestion.application.exception.TransactionNotFoundException;
import com.gestion.application.mapper.TransactionDetailMapper;
import com.gestion.application.model.TransactionDetail;
import com.gestion.application.repository.ProductRepository;
import com.gestion.application.repository.TransactionDetailRepository;
import com.gestion.application.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTransactionDetailImpl {

  private final TransactionDetailRepository detailRepo;
  private final TransactionRepository txRepo;
  private final ProductRepository productRepo;
  private final TransactionDetailMapper mapper;

  @Transactional
  public TransactionDetailResponse create(TransactionDetailRequest req) {
    if (req.getTransactionId() == null
        || req.getProductId() == null
        || req.getQuantity() == null
        || req.getQuantity() <= 0
        || req.getPrice() == null
        || req.getPrice().doubleValue() < 0) {
      throw new TransactionDetailInvalidDataException();
    }
    txRepo
        .findById(req.getTransactionId())
        .orElseThrow(() -> new TransactionNotFoundException(req.getTransactionId()));
    productRepo
        .findById(req.getProductId())
        .orElseThrow(() -> new ProductNotFoundException(req.getProductId()));

    TransactionDetail entity = mapper.toEntity(req);
    try {
      TransactionDetail saved = detailRepo.save(entity);
      return mapper.toResponse(saved);
    } catch (Exception ex) {
      throw new TransactionDetailCreationException();
    }
  }
}
