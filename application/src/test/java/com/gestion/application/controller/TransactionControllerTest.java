package com.gestion.application.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.*;
import com.gestion.application.service.transaction.TransactionService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

class TransactionControllerTest {

  @InjectMocks private TransactionController controller;

  @Mock private TransactionService service;

  private TransactionResponse transactionResponse;
  private TransactionSummaryDTO summaryDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    transactionResponse = new TransactionResponse();
    transactionResponse.setIdTransaction(1);
    transactionResponse.setTransactionDate(LocalDate.now());
    transactionResponse.setAmount(100.0);
    transactionResponse.setDescription("Pago");
    transactionResponse.setIsVisible(true);
    transactionResponse.setIdPatient(1);
    transactionResponse.setIdContactString("C123");

    summaryDTO = new TransactionSummaryDTO(1, LocalDate.now(), 100.0, null, 1, "Juan Pérez", 5);
  }

  @Test
  void testCreateTransaction() {
    TransactionRequest req = new TransactionRequest();
    when(service.createTransaction(req)).thenReturn(transactionResponse);

    var response = controller.createTransaction(req);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("success", response.getBody().getStatus());
  }

  @Test
  void testGetAllTransactions() {
    when(service.getAllTransactions()).thenReturn(List.of(transactionResponse));

    var response = controller.getAllTransactions();

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(1, response.getBody().getData().size());
  }

  @Test
  void testGetTransactionSummaries() {
    Pageable pageable = PageRequest.of(0, 10);
    Page<TransactionSummaryDTO> page = new PageImpl<>(List.of(summaryDTO));
    when(service.getTransactionSummaries(pageable)).thenReturn(page);

    var response = controller.getTransactionSummaries(pageable);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(1, response.getBody().getData().getContent().size());
  }

  @Test
  void testGetVisibleTransactions() {
    Pageable pageable = PageRequest.of(0, 10);
    Page<TransactionResponse> page = new PageImpl<>(List.of(transactionResponse));
    when(service.getVisibleTransactions(pageable)).thenReturn(page);

    var response = controller.getVisibleTransactions(pageable);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(1, response.getBody().getData().getContent().size());
  }

  @Test
  void testGetTransactionsByPatient() {
    when(service.getTransactionsByPatient(1)).thenReturn(List.of(transactionResponse));

    var response = controller.getTransactionsByPatient(1);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("success", response.getBody().getStatus());
  }

  @Test
  void testDeleteTransaction() {
    doNothing().when(service).deleteTransaction(1);

    var response = controller.deleteTransaction(1);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("Transacción eliminada", response.getBody().getData());
  }

  @Test
  void testMakeTransactionVisible() {
    when(service.makeTransactionVisible(1)).thenReturn(transactionResponse);

    var response = controller.makeTransactionVisible(1);

    assertEquals(200, response.getStatusCodeValue());
    assertTrue(response.getBody().getData().getIsVisible());
  }

  @Test
  void testMakeTransactionInvisible() {
    TransactionResponse invisibleResponse = new TransactionResponse();
    invisibleResponse.setIdTransaction(1);
    invisibleResponse.setIsVisible(false);

    when(service.makeTransactionInvisible(1)).thenReturn(invisibleResponse);

    var response = controller.makeTransactionInvisible(1);

    assertEquals(200, response.getStatusCodeValue());
    assertFalse(response.getBody().getData().getIsVisible());
  }

  @Test
  void testGetTransactionsByContact() {
    Pageable pageable = PageRequest.of(0, 10);
    Page<TransactionSummaryDTO> page = new PageImpl<>(List.of(summaryDTO));
    when(service.getTransactionSummariesByContact(1, pageable)).thenReturn(page);

    var response = controller.getTransactionsByContact(1, pageable);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(1, response.getBody().getData().getTotalElements());
  }

  @Test
  void testSearchTransactions() {
    Pageable pageable = PageRequest.of(0, 10);
    Page<TransactionResponse> page = new PageImpl<>(List.of(transactionResponse));
    when(service.searchTransactions("pago", pageable)).thenReturn(page);

    var response = controller.searchTransactions("pago", pageable);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("success", response.getBody().getStatus());
  }

  @Test
  void testGetTransactionInfo() {
    when(service.getTransactionInfo(1)).thenReturn(transactionResponse);

    var response = controller.getTransactionInfo(1);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(1, response.getBody().getData().getIdTransaction());
  }
}
