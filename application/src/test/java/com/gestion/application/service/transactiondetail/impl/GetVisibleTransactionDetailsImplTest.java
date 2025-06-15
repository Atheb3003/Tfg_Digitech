package com.gestion.application.service.transactiondetail.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.TransactionDetailResponse;
import com.gestion.application.mapper.TransactionDetailMapper;
import com.gestion.application.model.TransactionDetail;
import com.gestion.application.repository.TransactionDetailRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class GetVisibleTransactionDetailsImplTest {

  @Mock private TransactionDetailRepository detailRepo;

  @Mock private TransactionDetailMapper mapper;

  @InjectMocks private GetVisibleTransactionDetailsImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getVisible_shouldReturnMappedList() {
    TransactionDetail detail1 = new TransactionDetail();
    TransactionDetail detail2 = new TransactionDetail();

    TransactionDetailResponse resp1 = new TransactionDetailResponse();
    TransactionDetailResponse resp2 = new TransactionDetailResponse();

    when(detailRepo.findAllByIsVisibleTrue()).thenReturn(List.of(detail1, detail2));
    when(mapper.toResponse(detail1)).thenReturn(resp1);
    when(mapper.toResponse(detail2)).thenReturn(resp2);

    List<TransactionDetailResponse> result = service.getVisible();

    assertNotNull(result);
    assertEquals(2, result.size());
    assertTrue(result.contains(resp1));
    assertTrue(result.contains(resp2));

    verify(detailRepo).findAllByIsVisibleTrue();
  }
}
