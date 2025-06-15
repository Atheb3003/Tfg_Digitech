package com.gestion.application.service.transaction.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.exception.TransactionAlreadyInvisibleException;
import com.gestion.application.exception.TransactionNotFoundException;
import com.gestion.application.model.Transaction;
import com.gestion.application.repository.TransactionRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class MakeTransactionInvisibleImplTest {

  @Mock private TransactionRepository txRepo;

  @InjectMocks private MakeTransactionInvisibleImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void makeInvisible_shouldSetIsVisibleFalseAndSave_whenVisible() {
    Integer id = 1;
    Transaction tx = new Transaction();
    tx.setIsVisible(true);

    when(txRepo.findById(id)).thenReturn(Optional.of(tx));
    when(txRepo.save(tx)).thenReturn(tx);

    Transaction result = service.makeInvisible(id);

    assertFalse(result.getIsVisible());
    verify(txRepo).findById(id);
    verify(txRepo).save(tx);
  }

  @Test
  void makeInvisible_shouldThrowNotFound_whenTransactionDoesNotExist() {
    Integer id = 2;

    when(txRepo.findById(id)).thenReturn(Optional.empty());

    assertThrows(TransactionNotFoundException.class, () -> service.makeInvisible(id));

    verify(txRepo).findById(id);
    verify(txRepo, never()).save(any());
  }

  @Test
  void makeInvisible_shouldThrowAlreadyInvisible_whenIsVisibleFalse() {
    Integer id = 3;
    Transaction tx = new Transaction();
    tx.setIsVisible(false);

    when(txRepo.findById(id)).thenReturn(Optional.of(tx));

    assertThrows(TransactionAlreadyInvisibleException.class, () -> service.makeInvisible(id));

    verify(txRepo).findById(id);
    verify(txRepo, never()).save(any());
  }
}
