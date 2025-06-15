package com.gestion.application.service.transactiondetail.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.exception.TransactionDetailAlreadyVisibleException;
import com.gestion.application.exception.TransactionDetailNotFoundException;
import com.gestion.application.model.TransactionDetail;
import com.gestion.application.repository.TransactionDetailRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class MakeTransactionDetailVisibleImplTest {

  @Mock private TransactionDetailRepository detailRepo;

  @InjectMocks private MakeTransactionDetailVisibleImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void setVisible_shouldSetIsVisibleTrueAndSave_whenNotVisible() {
    Integer id = 1;
    TransactionDetail detail = new TransactionDetail();
    detail.setIsVisible(false);

    when(detailRepo.findById(id)).thenReturn(Optional.of(detail));
    when(detailRepo.save(detail)).thenReturn(detail);

    TransactionDetail result = service.setVisible(id);

    assertTrue(result.getIsVisible());
    verify(detailRepo).findById(id);
    verify(detailRepo).save(detail);
  }

  @Test
  void setVisible_shouldThrowNotFound_whenDetailDoesNotExist() {
    Integer id = 2;

    when(detailRepo.findById(id)).thenReturn(Optional.empty());

    assertThrows(TransactionDetailNotFoundException.class, () -> service.setVisible(id));

    verify(detailRepo).findById(id);
    verify(detailRepo, never()).save(any());
  }

  @Test
  void setVisible_shouldThrowAlreadyVisible_whenIsVisibleTrue() {
    Integer id = 3;
    TransactionDetail detail = new TransactionDetail();
    detail.setIsVisible(true);

    when(detailRepo.findById(id)).thenReturn(Optional.of(detail));

    assertThrows(TransactionDetailAlreadyVisibleException.class, () -> service.setVisible(id));

    verify(detailRepo).findById(id);
    verify(detailRepo, never()).save(any());
  }
}
