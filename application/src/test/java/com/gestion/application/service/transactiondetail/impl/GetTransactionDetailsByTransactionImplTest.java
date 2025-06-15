package com.gestion.application.service.transactiondetail.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.TransactionDetailResponse;
import com.gestion.application.exception.TransactionNotFoundException;
import com.gestion.application.mapper.TransactionDetailMapper;
import com.gestion.application.model.Transaction;
import com.gestion.application.model.TransactionDetail;
import com.gestion.application.repository.TransactionDetailRepository;
import com.gestion.application.repository.TransactionRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class GetTransactionDetailsByTransactionImplTest {

  @Mock private TransactionDetailRepository detailRepo;

  @Mock private TransactionRepository txRepo;

  @Mock private TransactionDetailMapper mapper;

  @InjectMocks private GetTransactionDetailsByTransactionImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getByTransaction_shouldReturnMappedList_whenTransactionExists() {
    Integer txId = 1;
    Transaction tx = new Transaction();
    TransactionDetail detail1 = new TransactionDetail();
    TransactionDetail detail2 = new TransactionDetail();

    TransactionDetailResponse resp1 = new TransactionDetailResponse();
    TransactionDetailResponse resp2 = new TransactionDetailResponse();

    when(txRepo.findById(txId)).thenReturn(Optional.of(tx));
    when(detailRepo.findByTransaction_IdTransaction(txId)).thenReturn(List.of(detail1, detail2));
    when(mapper.toResponse(detail1)).thenReturn(resp1);
    when(mapper.toResponse(detail2)).thenReturn(resp2);

    List<TransactionDetailResponse> result = service.getByTransaction(txId);

    assertNotNull(result);
    assertEquals(2, result.size());
    assertTrue(result.contains(resp1));
    assertTrue(result.contains(resp2));

    verify(txRepo).findById(txId);
    verify(detailRepo).findByTransaction_IdTransaction(txId);
  }

  @Test
  void getByTransaction_shouldThrowException_whenTransactionNotFound() {
    Integer txId = 99;

    when(txRepo.findById(txId)).thenReturn(Optional.empty());

    assertThrows(TransactionNotFoundException.class, () -> service.getByTransaction(txId));

    verify(txRepo).findById(txId);
    verifyNoInteractions(detailRepo, mapper);
  }
}
