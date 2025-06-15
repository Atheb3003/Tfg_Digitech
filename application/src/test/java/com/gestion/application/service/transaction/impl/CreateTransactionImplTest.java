package com.gestion.application.service.transaction.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.TransactionRequest;
import com.gestion.application.exception.TransactionInvalidDataException;
import com.gestion.application.mapper.TransactionMapper;
import com.gestion.application.repository.PatientRepository;
import com.gestion.application.repository.TransactionRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class CreateTransactionImplTest {

  @Mock private TransactionRepository txRepo;

  @Mock private PatientRepository patientRepo;

  @Mock private TransactionMapper mapper;

  @InjectMocks private CreateTransactionImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  private TransactionRequest validRequest() {
    TransactionRequest req = new TransactionRequest();
    req.setTransactionDate(LocalDate.now());
    req.setAmount(100.0);
    req.setDescription("Test payment");
    req.setIdPatient(1);
    return req;
  }

  @Test
  void create_shouldThrowException_whenTransactionDateIsNull() {
    TransactionRequest req = validRequest();
    req.setTransactionDate(null);

    assertThrows(TransactionInvalidDataException.class, () -> service.create(req));
    verifyNoInteractions(patientRepo, mapper, txRepo);
  }

  @Test
  void create_shouldThrowException_whenAmountIsNull() {
    TransactionRequest req = validRequest();
    req.setAmount(null);

    assertThrows(TransactionInvalidDataException.class, () -> service.create(req));
    verifyNoInteractions(patientRepo, mapper, txRepo);
  }

  @Test
  void create_shouldThrowException_whenAmountIsNegative() {
    TransactionRequest req = validRequest();
    req.setAmount(-10.0);

    assertThrows(TransactionInvalidDataException.class, () -> service.create(req));
    verifyNoInteractions(patientRepo, mapper, txRepo);
  }

  @Test
  void create_shouldThrowException_whenIdPatientIsNull() {
    TransactionRequest req = validRequest();
    req.setIdPatient(null);

    assertThrows(TransactionInvalidDataException.class, () -> service.create(req));
    verifyNoInteractions(patientRepo, mapper, txRepo);
  }

  @Test
  void create_shouldThrowException_whenPaymentMethodIsNull() {
    TransactionRequest req = validRequest();
    req.setPaymentMethod(null);

    assertThrows(TransactionInvalidDataException.class, () -> service.create(req));
    verifyNoInteractions(patientRepo, mapper, txRepo);
  }
}
