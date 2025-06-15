package com.gestion.application.service.transaction.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.TransactionSummaryDTO;
import com.gestion.application.model.Contact;
import com.gestion.application.model.Patient;
import com.gestion.application.model.Transaction;
import com.gestion.application.repository.TransactionRepository;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

class GetTransactionSummariesImplTest {

  @Mock private TransactionRepository transactionRepository;

  @InjectMocks private GetTransactionSummariesImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void execute_shouldReturnPagedTransactionSummaryDTO() {
    Pageable pageable = PageRequest.of(0, 10);

    Contact contact = new Contact();
    contact.setIdContact(900);
    contact.setName("Helen");
    contact.setSurname("Keller");

    Patient patient = new Patient();
    patient.setIdPatient(90);
    patient.setContact(contact);

    Transaction tx = new Transaction();
    tx.setIdTransaction(9000);
    tx.setTransactionDate(LocalDate.of(2025, 8, 20));
    tx.setAmount(750.0);

    tx.setPatient(patient);

    Page<Transaction> page = new PageImpl<>(List.of(tx), pageable, 1);

    when(transactionRepository.findAll(pageable)).thenReturn(page);

    Page<TransactionSummaryDTO> result = service.execute(pageable);

    assertNotNull(result);
    assertEquals(1, result.getTotalElements());

    TransactionSummaryDTO dto = result.getContent().get(0);

    assertEquals(tx.getIdTransaction(), dto.getIdTransaction());
    assertEquals(tx.getTransactionDate(), dto.getTransactionDate());
    assertEquals(tx.getAmount(), dto.getAmount());
    assertEquals(tx.getPaymentMethod(), dto.getPaymentMethod());
    assertEquals(patient.getIdPatient(), dto.getIdPatient());
    assertEquals("Helen Keller", dto.getFullName());
    assertEquals(contact.getIdContact(), dto.getIdContact());

    verify(transactionRepository).findAll(pageable);
  }
}
