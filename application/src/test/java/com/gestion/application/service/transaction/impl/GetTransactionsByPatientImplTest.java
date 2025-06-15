package com.gestion.application.service.transaction.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.TransactionResponse;
import com.gestion.application.exception.PatientNotFoundException;
import com.gestion.application.mapper.TransactionMapper;
import com.gestion.application.model.Patient;
import com.gestion.application.model.Transaction;
import com.gestion.application.repository.PatientRepository;
import com.gestion.application.repository.TransactionRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class GetTransactionsByPatientImplTest {

  @Mock private TransactionRepository txRepo;

  @Mock private PatientRepository patientRepo;

  @Mock private TransactionMapper mapper;

  @InjectMocks private GetTransactionsByPatientImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getTransactionsByPatient_shouldReturnMappedList_whenPatientExists() {
    Integer patientId = 1;
    Patient patient = new Patient();
    patient.setIdPatient(patientId);

    Transaction tx1 = new Transaction();
    Transaction tx2 = new Transaction();

    TransactionResponse resp1 = new TransactionResponse();
    TransactionResponse resp2 = new TransactionResponse();

    when(patientRepo.findById(patientId)).thenReturn(Optional.of(patient));
    when(txRepo.findByPatient_IdPatient(patientId)).thenReturn(List.of(tx1, tx2));
    when(mapper.toResponse(tx1)).thenReturn(resp1);
    when(mapper.toResponse(tx2)).thenReturn(resp2);

    List<TransactionResponse> result = service.getTransactionsByPatient(patientId);

    assertNotNull(result);
    assertEquals(2, result.size());
    assertTrue(result.contains(resp1));
    assertTrue(result.contains(resp2));

    verify(patientRepo).findById(patientId);
    verify(txRepo).findByPatient_IdPatient(patientId);
  }

  @Test
  void getTransactionsByPatient_shouldThrowException_whenPatientNotFound() {
    Integer patientId = 99;

    when(patientRepo.findById(patientId)).thenReturn(Optional.empty());

    assertThrows(PatientNotFoundException.class, () -> service.getTransactionsByPatient(patientId));

    verify(patientRepo).findById(patientId);
    verifyNoInteractions(txRepo, mapper);
  }
}
