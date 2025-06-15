package com.gestion.application.service.transaction.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.exception.TransactionNotFoundException;
import com.gestion.application.repository.TransactionDetailRepository;
import com.gestion.application.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class DeleteTransactionImplTest {

  @Mock private TransactionRepository txRepo;

  @Mock private TransactionDetailRepository detailRepo;

  @InjectMocks private DeleteTransactionImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void deleteTransaction_shouldDeleteDetailsAndTransaction_whenExists() {
    Integer id = 1;

    when(txRepo.existsById(id)).thenReturn(true);
    doNothing().when(detailRepo).deleteByTransaction_IdTransaction(id);
    doNothing().when(txRepo).deleteById(id);

    service.deleteTransaction(id);

    verify(txRepo).existsById(id);
    verify(detailRepo).deleteByTransaction_IdTransaction(id);
    verify(txRepo).deleteById(id);
  }

  @Test
  void deleteTransaction_shouldThrowException_whenTransactionNotExists() {
    Integer id = 2;

    when(txRepo.existsById(id)).thenReturn(false);

    assertThrows(TransactionNotFoundException.class, () -> service.deleteTransaction(id));

    verify(txRepo).existsById(id);
    verify(detailRepo, never()).deleteByTransaction_IdTransaction(anyInt());
    verify(txRepo, never()).deleteById(anyInt());
  }
}
