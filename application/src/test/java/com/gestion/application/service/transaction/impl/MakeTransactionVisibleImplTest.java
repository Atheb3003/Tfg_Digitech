package com.gestion.application.service.transaction.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.exception.TransactionAlreadyVisibleException;
import com.gestion.application.exception.TransactionNotFoundException;
import com.gestion.application.model.Transaction;
import com.gestion.application.repository.TransactionRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class MakeTransactionVisibleImplTest {

  @Mock private TransactionRepository txRepo;

  @InjectMocks private MakeTransactionVisibleImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void makeVisible_shouldSetIsVisibleTrueAndSave_whenInvisible() {
    Integer id = 1;
    Transaction tx = new Transaction();
    tx.setIsVisible(false);

    when(txRepo.findById(id)).thenReturn(Optional.of(tx));
    when(txRepo.save(tx)).thenReturn(tx);

    Transaction result = service.makeVisible(id);

    assertTrue(result.getIsVisible());
    verify(txRepo).findById(id);
    verify(txRepo).save(tx);
  }

  @Test
  void makeVisible_shouldThrowNotFound_whenTransactionDoesNotExist() {
    Integer id = 2;

    when(txRepo.findById(id)).thenReturn(Optional.empty());

    assertThrows(TransactionNotFoundException.class, () -> service.makeVisible(id));

    verify(txRepo).findById(id);
    verify(txRepo, never()).save(any());
  }

  @Test
  void makeVisible_shouldThrowAlreadyVisible_whenIsVisibleTrue() {
    Integer id = 3;
    Transaction tx = new Transaction();
    tx.setIsVisible(true);

    when(txRepo.findById(id)).thenReturn(Optional.of(tx));

    assertThrows(TransactionAlreadyVisibleException.class, () -> service.makeVisible(id));

    verify(txRepo).findById(id);
    verify(txRepo, never()).save(any());
  }
}
