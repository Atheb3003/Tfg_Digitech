package com.gestion.application.service.transaction.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.TransactionResponse;
import com.gestion.application.mapper.TransactionMapper;
import com.gestion.application.model.Transaction;
import com.gestion.application.repository.TransactionRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class GetAllTransactionsImplTest {

  @Mock private TransactionRepository txRepo;

  @Mock private TransactionMapper mapper;

  @InjectMocks private GetAllTransactionsImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getAllTransactions_shouldReturnMappedList() {
    Transaction tx1 = new Transaction();
    Transaction tx2 = new Transaction();

    TransactionResponse resp1 = new TransactionResponse();
    TransactionResponse resp2 = new TransactionResponse();

    List<Transaction> transactions = List.of(tx1, tx2);

    when(txRepo.findAll()).thenReturn(transactions);
    when(mapper.toResponse(tx1)).thenReturn(resp1);
    when(mapper.toResponse(tx2)).thenReturn(resp2);

    List<TransactionResponse> result = service.getAllTransactions();

    assertNotNull(result);
    assertEquals(2, result.size());
    assertTrue(result.contains(resp1));
    assertTrue(result.contains(resp2));

    verify(txRepo).findAll();
  }
}
