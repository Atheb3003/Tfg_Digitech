package com.gestion.application.service.transaction.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.TransactionResponse;
import com.gestion.application.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

class SearchTransactionsImplTest {

  @Mock private TransactionRepository transactionRepository;

  @InjectMocks private SearchTransactionsImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void searchVisibleTransactions_shouldReturnPageFromRepository() {
    Pageable pageable = PageRequest.of(0, 10);
    String search = "keyword";

    Page<TransactionResponse> page = Page.empty(pageable);

    when(transactionRepository.searchVisibleTransactions(search, pageable)).thenReturn(page);

    Page<TransactionResponse> result = service.searchVisibleTransactions(search, pageable);

    assertNotNull(result);
    assertEquals(0, result.getTotalElements());

    verify(transactionRepository).searchVisibleTransactions(search, pageable);
  }
}
