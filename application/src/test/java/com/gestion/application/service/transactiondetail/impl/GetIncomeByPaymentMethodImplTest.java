package com.gestion.application.service.transactiondetail.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.IncomeByPaymentMethodResponse;
import com.gestion.application.repository.TransactionDetailRepository;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class GetIncomeByPaymentMethodImplTest {

  @Mock private TransactionDetailRepository transactionDetailRepository;

  @InjectMocks private GetIncomeByPaymentMethodImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getIncomeByPaymentMethod_shouldReturnZeroIfNoDetails() {
    LocalDate start = LocalDate.of(2025, 1, 1);
    LocalDate end = LocalDate.of(2025, 12, 31);

    when(transactionDetailRepository.findAllByIsVisibleTrue()).thenReturn(List.of());

    IncomeByPaymentMethodResponse response = service.getIncomeByPaymentMethod(start, end);

    assertNotNull(response);
    assertEquals(start, response.getStartDate());
    assertEquals(end, response.getEndDate());
  }
}
