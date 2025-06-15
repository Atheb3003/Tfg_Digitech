package com.gestion.application.service.transactiondetail.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.exception.TransactionDetailNotFoundException;
import com.gestion.application.repository.TransactionDetailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class DeleteTransactionDetailImplTest {

  @Mock private TransactionDetailRepository detailRepo;

  @InjectMocks private DeleteTransactionDetailImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void deleteDetail_shouldDelete_whenExists() {
    Integer id = 1;

    when(detailRepo.existsById(id)).thenReturn(true);
    doNothing().when(detailRepo).deleteById(id);

    service.deleteDetail(id);

    verify(detailRepo).existsById(id);
    verify(detailRepo).deleteById(id);
  }

  @Test
  void deleteDetail_shouldThrowException_whenNotExists() {
    Integer id = 2;

    when(detailRepo.existsById(id)).thenReturn(false);

    assertThrows(TransactionDetailNotFoundException.class, () -> service.deleteDetail(id));

    verify(detailRepo).existsById(id);
    verify(detailRepo, never()).deleteById(anyInt());
  }
}
