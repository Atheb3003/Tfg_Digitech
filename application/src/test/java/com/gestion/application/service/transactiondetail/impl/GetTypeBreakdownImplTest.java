package com.gestion.application.service.transactiondetail.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.TypeBreakdownResponse;
import com.gestion.application.model.*;
import com.gestion.application.repository.TransactionDetailRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class GetTypeBreakdownImplTest {

  @Mock private TransactionDetailRepository transactionDetailRepository;

  @InjectMocks private GetTypeBreakdownImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getTypeBreakdown_shouldHandleNullDatesAndReturnEmptyIfNoData() {
    when(transactionDetailRepository.findAllByIsVisibleTrue()).thenReturn(List.of());

    TypeBreakdownResponse resp = service.getTypeBreakdown(null, null);

    assertNotNull(resp);
    assertTrue(resp.getTypes().isEmpty());
    assertEquals(LocalDate.now().toString(), resp.getStartDate());
    assertEquals(LocalDate.now().toString(), resp.getEndDate());
  }

  @Test
  void getTypeBreakdown_shouldCalculateTotalsByType() {
    LocalDate start = LocalDate.of(2025, 1, 1);
    LocalDate end = LocalDate.of(2025, 12, 31);

    Transaction tx1 = new Transaction();
    tx1.setTransactionDate(LocalDate.of(2025, 6, 10));

    Transaction tx2 = new Transaction();
    tx2.setTransactionDate(LocalDate.of(2025, 7, 10));

    Product product = new Product();
    product.setName("Producto");

    ProtocolTreatment pt = new ProtocolTreatment();
    pt.setIsFinished(true);

    SurgeryReservation sr = new SurgeryReservation();

    TransactionDetail detailProduct = new TransactionDetail();
    detailProduct.setTransaction(tx1);
    detailProduct.setProduct(product);
    detailProduct.setQuantity(2);
    detailProduct.setPrice(BigDecimal.valueOf(100));

    TransactionDetail detailProtocol = new TransactionDetail();
    detailProtocol.setTransaction(tx2);
    detailProtocol.setProtocolTreatment(pt);
    detailProtocol.setQuantity(3);
    detailProtocol.setPrice(BigDecimal.valueOf(50));

    TransactionDetail detailSurgery = new TransactionDetail();
    detailSurgery.setTransaction(tx2);
    detailSurgery.setSurgeryReservation(sr);
    detailSurgery.setQuantity(null); // should default to 1
    detailSurgery.setPrice(BigDecimal.valueOf(200));

    when(transactionDetailRepository.findAllByIsVisibleTrue())
        .thenReturn(List.of(detailProduct, detailProtocol, detailSurgery));
    when(transactionDetailRepository.findAllByIsVisibleAndTransactionDateBetween(start, end))
        .thenReturn(List.of(detailProduct, detailProtocol, detailSurgery));

    TypeBreakdownResponse resp = service.getTypeBreakdown(start, end);

    assertNotNull(resp);
    assertEquals(start.toString(), resp.getStartDate());
    assertEquals(end.toString(), resp.getEndDate());

    List<TypeBreakdownResponse.TypeSummary> types = resp.getTypes();
    assertEquals(3, types.size());

    var productSummary =
        types.stream().filter(t -> t.getType().equals("PRODUCT")).findFirst().orElse(null);
    assertNotNull(productSummary);
    assertEquals(2, productSummary.getQuantity());
    assertEquals("200,00", productSummary.getTotalIncome()); // 2 * 100

    var protocolSummary =
        types.stream()
            .filter(t -> t.getType().equals("PROTOCOL_TREATMENT"))
            .findFirst()
            .orElse(null);
    assertNotNull(protocolSummary);
    assertEquals(3, protocolSummary.getQuantity());
    assertEquals("150,00", protocolSummary.getTotalIncome()); // 3 * 50

    var surgerySummary =
        types.stream().filter(t -> t.getType().equals("SURGERY")).findFirst().orElse(null);
    assertNotNull(surgerySummary);
    assertEquals(1, surgerySummary.getQuantity()); // quantity null defaults to 1
    assertEquals("200,00", surgerySummary.getTotalIncome());
  }
}
