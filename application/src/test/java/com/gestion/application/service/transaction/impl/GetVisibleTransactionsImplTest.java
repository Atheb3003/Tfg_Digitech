package com.gestion.application.service.transaction.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.TransactionResponse;
import com.gestion.application.mapper.TransactionMapper;
import com.gestion.application.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

class GetVisibleTransactionsImplTest {

  @Mock private TransactionRepository transactionRepository;

  @Mock private TransactionMapper mapper;

  @InjectMocks private GetVisibleTransactionsImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getVisibleTransactions_shouldReturnPageFromRepository() {
    Pageable pageable = PageRequest.of(0, 10);

    Page<TransactionResponse> page = Page.empty(pageable);

    when(transactionRepository.findAllVisibleTransactions(pageable)).thenReturn(page);

    Page<TransactionResponse> result = service.getVisibleTransactions(pageable);

    assertNotNull(result);
    assertEquals(0, result.getTotalElements());

    verify(transactionRepository).findAllVisibleTransactions(pageable);
  }
}
