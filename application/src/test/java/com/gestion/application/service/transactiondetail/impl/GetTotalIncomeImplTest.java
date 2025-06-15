package com.gestion.application.service.transactiondetail.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.TotalIncomeResponse;
import com.gestion.application.model.Transaction;
import com.gestion.application.model.TransactionDetail;
import com.gestion.application.repository.TransactionDetailRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class GetTotalIncomeImplTest {

  @Mock private TransactionDetailRepository transactionDetailRepository;

  @InjectMocks private GetTotalIncomeImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getTotalIncome_shouldReturnZero_whenNoDetails() {
    LocalDate start = LocalDate.of(2025, 1, 1);
    LocalDate end = LocalDate.of(2025, 12, 31);

    when(transactionDetailRepository.findAllByIsVisibleTrue()).thenReturn(List.of());

    TotalIncomeResponse response = service.getTotalIncome(start, end);

    assertNotNull(response);
    assertEquals(start, response.getStartDate());
    assertEquals(end, response.getEndDate());
  }

  @Test
  void getTotalIncome_shouldCalculateTotalCorrectly() {
    LocalDate start = LocalDate.of(2025, 1, 1);
    LocalDate end = LocalDate.of(2025, 12, 31);

    Transaction tx1 = new Transaction();
    tx1.setTransactionDate(LocalDate.of(2025, 6, 10));

    Transaction tx2 = new Transaction();
    tx2.setTransactionDate(LocalDate.of(2025, 7, 15));

    TransactionDetail detail1 = new TransactionDetail();
    detail1.setTransaction(tx1);
    detail1.setPrice(BigDecimal.valueOf(100));
    detail1.setQuantity(2);

    TransactionDetail detail2 = new TransactionDetail();
    detail2.setTransaction(tx2);
    detail2.setPrice(BigDecimal.valueOf(50));
    detail2.setQuantity(null);

    when(transactionDetailRepository.findAllByIsVisibleTrue())
        .thenReturn(List.of(detail1, detail2));

    TotalIncomeResponse response = service.getTotalIncome(start, end);

    assertNotNull(response);
    assertEquals(start, response.getStartDate());
    assertEquals(end, response.getEndDate());
  }

  @Test
  void getTotalIncome_shouldFilterByDateRange() {
    LocalDate start = LocalDate.of(2025, 6, 1);
    LocalDate end = LocalDate.of(2025, 6, 30);

    Transaction tx1 = new Transaction();
    tx1.setTransactionDate(LocalDate.of(2025, 6, 15));

    Transaction tx2 = new Transaction();
    tx2.setTransactionDate(LocalDate.of(2025, 7, 15));

    TransactionDetail detail1 = new TransactionDetail();
    detail1.setTransaction(tx1);
    detail1.setPrice(BigDecimal.valueOf(100));
    detail1.setQuantity(1);

    TransactionDetail detail2 = new TransactionDetail();
    detail2.setTransaction(tx2);
    detail2.setPrice(BigDecimal.valueOf(200));
    detail2.setQuantity(1);

    when(transactionDetailRepository.findAllByIsVisibleTrue())
        .thenReturn(List.of(detail1, detail2));

    TotalIncomeResponse response = service.getTotalIncome(start, end);

    assertNotNull(response);
    assertEquals(start, response.getStartDate());
    assertEquals(end, response.getEndDate());
  }

  @Test
  void getTotalIncome_shouldUseMinMaxDates_whenStartOrEndNull() {
    Transaction tx1 = new Transaction();
    tx1.setTransactionDate(LocalDate.of(2025, 3, 10));

    Transaction tx2 = new Transaction();
    tx2.setTransactionDate(LocalDate.of(2025, 10, 10));

    TransactionDetail detail1 = new TransactionDetail();
    detail1.setTransaction(tx1);
    detail1.setPrice(BigDecimal.valueOf(100));
    detail1.setQuantity(1);

    TransactionDetail detail2 = new TransactionDetail();
    detail2.setTransaction(tx2);
    detail2.setPrice(BigDecimal.valueOf(200));
    detail2.setQuantity(1);

    when(transactionDetailRepository.findAllByIsVisibleTrue())
        .thenReturn(List.of(detail1, detail2));

    // Pass null for startDate and endDate to trigger auto date range
    TotalIncomeResponse response = service.getTotalIncome(null, null);

    assertNotNull(response);
    // The start date should be the min date: 2025-03-10
    assertEquals(tx1.getTransactionDate(), response.getStartDate());
    // The end date should be the max date: 2025-10-10
    assertEquals(tx2.getTransactionDate(), response.getEndDate());
  }
}
