package com.gestion.application.service.transactiondetail.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.SoldItemResponse;
import com.gestion.application.model.*;
import com.gestion.application.repository.TransactionDetailRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

class GetVisibleSoldItemsImplTest {

  @Mock private TransactionDetailRepository transactionDetailRepository;

  @InjectMocks private GetVisibleSoldItemsImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  private TransactionDetail createDetailProduct(int quantity, LocalDate date, int txId) {
    Product product = new Product();
    product.setName("Product X");
    Patient patient = new Patient();
    Contact contact = new Contact();
    contact.setIdContactString("contact-123");
    patient.setContact(contact);
    Transaction tx = new Transaction();
    tx.setIdTransaction(txId);
    tx.setTransactionDate(date);
    tx.setPatient(patient);

    TransactionDetail detail = new TransactionDetail();
    detail.setProduct(product);
    detail.setQuantity(quantity);
    detail.setPrice(BigDecimal.valueOf(10.0));
    detail.setTransaction(tx);
    return detail;
  }

  @Test
  void getVisibleSoldItems_shouldExpandSortAndPaginate() {
    TransactionDetail detail1 = createDetailProduct(2, LocalDate.of(2025, 6, 10), 1);
    TransactionDetail detail2 = createDetailProduct(1, LocalDate.of(2025, 7, 15), 2);

    when(transactionDetailRepository.findAllByIsVisibleTrue())
        .thenReturn(List.of(detail1, detail2));

    Pageable pageable = PageRequest.of(0, 2);

    Page<SoldItemResponse> page = service.getVisibleSoldItems(pageable);

    assertNotNull(page);
    assertEquals(2, page.getContent().size()); // page size 2

    // Should be sorted by date descending
    assertTrue(
        page.getContent()
                .get(0)
                .getTransactionDate()
                .isAfter(page.getContent().get(1).getTransactionDate())
            || page.getContent()
                .get(0)
                .getTransactionDate()
                .isEqual(page.getContent().get(1).getTransactionDate()));

    // Expanded quantity: detail1 (2 items) + detail2 (1 item) = 3 total, but page size limits to 2
    assertEquals(3, page.getTotalElements());

    // Verify first item's data
    SoldItemResponse first = page.getContent().get(0);
    assertEquals("PRODUCT", first.getType());
    assertEquals("Product X", first.getName());
    assertEquals("contact-123", first.getIdContactString());
    assertEquals(10.0, first.getAmount());
  }

  @Test
  void getVisibleSoldItems_shouldHandleQuantityNullAsOne() {
    TransactionDetail detail = createDetailProduct(0, LocalDate.of(2025, 6, 10), 1);
    detail.setQuantity(null);

    when(transactionDetailRepository.findAllByIsVisibleTrue()).thenReturn(List.of(detail));

    Pageable pageable = PageRequest.of(0, 10);

    Page<SoldItemResponse> page = service.getVisibleSoldItems(pageable);

    assertNotNull(page);
    assertEquals(1, page.getContent().size()); // quantity null treated as 1
  }

  @Test
  void getVisibleSoldItems_shouldReturnEmptyPage_whenNoDetails() {
    when(transactionDetailRepository.findAllByIsVisibleTrue()).thenReturn(List.of());

    Pageable pageable = PageRequest.of(0, 10);

    Page<SoldItemResponse> page = service.getVisibleSoldItems(pageable);

    assertNotNull(page);
    assertTrue(page.getContent().isEmpty());
  }
}
