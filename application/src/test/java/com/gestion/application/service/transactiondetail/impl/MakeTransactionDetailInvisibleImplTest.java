package com.gestion.application.service.transactiondetail.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.exception.TransactionDetailAlreadyInvisibleException;
import com.gestion.application.exception.TransactionDetailNotFoundException;
import com.gestion.application.model.TransactionDetail;
import com.gestion.application.repository.TransactionDetailRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class MakeTransactionDetailInvisibleImplTest {

  @Mock private TransactionDetailRepository detailRepo;

  @InjectMocks private MakeTransactionDetailInvisibleImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void setInvisible_shouldSetIsVisibleFalseAndSave_whenVisible() {
    Integer id = 1;
    TransactionDetail detail = new TransactionDetail();
    detail.setIsVisible(true);

    when(detailRepo.findById(id)).thenReturn(Optional.of(detail));
    when(detailRepo.save(detail)).thenReturn(detail);

    TransactionDetail result = service.setInvisible(id);

    assertFalse(result.getIsVisible());
    verify(detailRepo).findById(id);
    verify(detailRepo).save(detail);
  }

  @Test
  void setInvisible_shouldThrowNotFound_whenDetailDoesNotExist() {
    Integer id = 2;

    when(detailRepo.findById(id)).thenReturn(Optional.empty());

    assertThrows(TransactionDetailNotFoundException.class, () -> service.setInvisible(id));

    verify(detailRepo).findById(id);
    verify(detailRepo, never()).save(any());
  }

  @Test
  void setInvisible_shouldThrowAlreadyInvisible_whenIsVisibleFalse() {
    Integer id = 3;
    TransactionDetail detail = new TransactionDetail();
    detail.setIsVisible(false);

    when(detailRepo.findById(id)).thenReturn(Optional.of(detail));

    assertThrows(TransactionDetailAlreadyInvisibleException.class, () -> service.setInvisible(id));

    verify(detailRepo).findById(id);
    verify(detailRepo, never()).save(any());
  }
}
