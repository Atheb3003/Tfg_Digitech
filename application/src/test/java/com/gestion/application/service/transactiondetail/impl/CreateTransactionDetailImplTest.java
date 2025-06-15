package com.gestion.application.service.transactiondetail.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.TransactionDetailRequest;
import com.gestion.application.exception.TransactionNotFoundException;
import com.gestion.application.mapper.TransactionDetailMapper;
import com.gestion.application.repository.ProductRepository;
import com.gestion.application.repository.TransactionDetailRepository;
import com.gestion.application.repository.TransactionRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class CreateTransactionDetailImplTest {

  @Mock private TransactionDetailRepository detailRepo;

  @Mock private TransactionRepository txRepo;

  @Mock private ProductRepository productRepo;

  @Mock private TransactionDetailMapper mapper;

  @InjectMocks private CreateTransactionDetailImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  private TransactionDetailRequest validRequest() {
    TransactionDetailRequest req = new TransactionDetailRequest();
    req.setTransactionId(1);
    req.setQuantity(5);
    req.setPrice(BigDecimal.valueOf(100));
    req.setProductId(10);
    return req;
  }

  @Test
  void create_shouldThrowTransactionNotFoundException_whenTransactionMissing() {
    TransactionDetailRequest req = validRequest();

    when(txRepo.findById(req.getTransactionId())).thenReturn(Optional.empty());

    assertThrows(TransactionNotFoundException.class, () -> service.create(req));

    verify(txRepo).findById(req.getTransactionId());
    verifyNoMoreInteractions(txRepo);
    verifyNoInteractions(productRepo, mapper, detailRepo);
  }
}
