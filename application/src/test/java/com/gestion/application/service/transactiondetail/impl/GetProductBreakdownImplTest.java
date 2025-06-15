package com.gestion.application.service.transactiondetail.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ProductBreakdownItemResponse;
import com.gestion.application.dto.ProductBreakdownResponse;
import com.gestion.application.model.*;
import com.gestion.application.repository.TransactionDetailRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class GetProductBreakdownImplTest {

  @Mock private TransactionDetailRepository transactionDetailRepository;

  @InjectMocks private GetProductBreakdownImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getProductBreakdown_shouldAggregateAndSortByQuantity() {
    LocalDate start = LocalDate.of(2025, 1, 1);
    LocalDate end = LocalDate.of(2025, 12, 31);

    Product prodA = new Product();
    prodA.setName("Producto A");

    ProtocolTreatment protocolTreatment = new ProtocolTreatment();
    Product prodB = new Product();
    prodB.setName("Producto B");
    protocolTreatment.setProduct(prodB);

    SurgeryReservation surgeryReservation = new SurgeryReservation();
    surgeryReservation.setSurgicalTechnique("Técnica X");
    surgeryReservation.setFollicularUnits(100);

    Transaction tx = new Transaction();
    tx.setTransactionDate(LocalDate.of(2025, 6, 15));

    TransactionDetail detail1 = new TransactionDetail();
    detail1.setProduct(prodA);
    detail1.setQuantity(2);
    detail1.setPrice(BigDecimal.valueOf(50));
    detail1.setTransaction(tx);

    TransactionDetail detail2 = new TransactionDetail();
    detail2.setProtocolTreatment(protocolTreatment);
    detail2.setQuantity(3);
    detail2.setPrice(BigDecimal.valueOf(30));
    detail2.setTransaction(tx);

    TransactionDetail detail3 = new TransactionDetail();
    detail3.setSurgeryReservation(surgeryReservation);
    detail3.setQuantity(1);
    detail3.setPrice(BigDecimal.valueOf(100));
    detail3.setTransaction(tx);

    // Another detail with same product as detail1, to test aggregation
    TransactionDetail detail4 = new TransactionDetail();
    detail4.setProduct(prodA);
    detail4.setQuantity(1);
    detail4.setPrice(BigDecimal.valueOf(50));
    detail4.setTransaction(tx);

    when(transactionDetailRepository.findAllByIsVisibleAndTransactionDateBetween(start, end))
        .thenReturn(List.of(detail1, detail2, detail3, detail4));

    ProductBreakdownResponse response = service.getProductBreakdown(start, end);

    assertNotNull(response);
    assertEquals(start.toString(), response.getStartDate());
    assertEquals(end.toString(), response.getEndDate());
    List<ProductBreakdownItemResponse> items = response.getItems();
    assertEquals(3, items.size());

    // Check aggregation for Producto A (detail1 + detail4)
    ProductBreakdownItemResponse prodAItem =
        items.stream()
            .filter(i -> "PRODUCT".equals(i.getType()) && "Producto A".equals(i.getName()))
            .findFirst()
            .orElse(null);
    assertNotNull(prodAItem);
    assertEquals(3, prodAItem.getQuantity()); // 2 + 1
    assertEquals("150,00", prodAItem.getIncome()); // 50*2 + 50*1 = 150

    // Check Protocol Treatment aggregation
    ProductBreakdownItemResponse protocolItem =
        items.stream()
            .filter(i -> "PROTOCOL_TREATMENT".equals(i.getType()))
            .findFirst()
            .orElse(null);
    assertNotNull(protocolItem);
    assertEquals(3, protocolItem.getQuantity());
    assertEquals("90,00", protocolItem.getIncome()); // 30*3

    // Check Surgery aggregation
    ProductBreakdownItemResponse surgeryItem =
        items.stream().filter(i -> "SURGERY".equals(i.getType())).findFirst().orElse(null);
    assertNotNull(surgeryItem);
    assertEquals(1, surgeryItem.getQuantity());
    assertEquals("100,00", surgeryItem.getIncome());

    // Check order by quantity descending
    assertEquals(items.get(0), prodAItem);
    assertEquals(items.get(1), protocolItem);
    assertEquals(items.get(2), surgeryItem);
  }

  @Test
  void getProductBreakdown_shouldUseDefaultsWhenDatesNull() {
    when(transactionDetailRepository.findAllByIsVisibleAndTransactionDateBetween(any(), any()))
        .thenReturn(List.of());

    ProductBreakdownResponse response = service.getProductBreakdown(null, null);

    assertNotNull(response);
    assertEquals("1970-01-01", response.getStartDate());
    assertEquals(LocalDate.now().toString(), response.getEndDate());
    assertTrue(response.getItems().isEmpty());

    verify(transactionDetailRepository).findAllByIsVisibleAndTransactionDateBetween(any(), any());
  }
}
