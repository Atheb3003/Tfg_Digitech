package com.gestion.application.service.transaction.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.TransactionResponse;
import com.gestion.application.model.Contact;
import com.gestion.application.model.Patient;
import com.gestion.application.model.Transaction;
import com.gestion.application.repository.TransactionRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class GetTransactionInfoImplTest {

  @Mock private TransactionRepository transactionRepository;

  @InjectMocks private GetTransactionInfoImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getTransactionInfo_shouldReturnDto_whenTransactionExists() {
    Integer id = 1;

    Contact contact = new Contact();
    contact.setIdContactString("CT123");

    Patient patient = new Patient();
    patient.setIdPatient(10);
    patient.setContact(contact);

    Transaction tx = new Transaction();
    tx.setIdTransaction(id);
    tx.setTransactionDate(LocalDate.of(2025, 6, 14));
    tx.setAmount(150.0);
    tx.setDescription("Payment");
    tx.setIsVisible(true);
    tx.setPatient(patient);

    when(transactionRepository.findById(id)).thenReturn(Optional.of(tx));

    TransactionResponse dto = service.getTransactionInfo(id);

    assertNotNull(dto);
    assertEquals(id, dto.getIdTransaction());
    assertEquals(tx.getTransactionDate(), dto.getTransactionDate());
    assertEquals(tx.getAmount(), dto.getAmount());
    assertEquals(tx.getDescription(), dto.getDescription());
    assertEquals(tx.getPaymentMethod(), dto.getPaymentMethod());
    assertEquals(tx.getIsVisible(), dto.getIsVisible());
    assertEquals(patient.getIdPatient(), dto.getIdPatient());
    assertEquals(contact.getIdContactString(), dto.getIdContactString());

    verify(transactionRepository).findById(id);
  }

  @Test
  void getTransactionInfo_shouldThrowException_whenTransactionNotFound() {
    Integer id = 999;

    when(transactionRepository.findById(id)).thenReturn(Optional.empty());

    RuntimeException ex =
        assertThrows(RuntimeException.class, () -> service.getTransactionInfo(id));

    assertTrue(ex.getMessage().contains("No existe la transacción con id " + id));

    verify(transactionRepository).findById(id);
  }
}
