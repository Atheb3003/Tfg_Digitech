package com.gestion.application.service.patient.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.model.Patient;
import com.gestion.application.repository.PatientRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class GetPatientByContactIdImplTest {

  @Mock private PatientRepository patientRepository;

  @InjectMocks private GetPatientByContactIdImpl getPatientByContactId;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldReturnPatientIdWhenPatientExists() {
    // Given
    Integer contactId = 1;
    Integer patientId = 10;

    Patient patient = new Patient();
    patient.setIdPatient(patientId);

    when(patientRepository.findByContact_IdContact(contactId)).thenReturn(Optional.of(patient));

    // When
    Optional<Integer> result = getPatientByContactId.getPatientIdByContact(contactId);

    // Then
    assertTrue(result.isPresent());
    assertEquals(patientId, result.get());
    verify(patientRepository).findByContact_IdContact(contactId);
  }

  @Test
  void shouldReturnEmptyWhenPatientDoesNotExist() {
    // Given
    Integer contactId = 2;
    when(patientRepository.findByContact_IdContact(contactId)).thenReturn(Optional.empty());

    // When
    Optional<Integer> result = getPatientByContactId.getPatientIdByContact(contactId);

    // Then
    assertTrue(result.isEmpty());
    verify(patientRepository).findByContact_IdContact(contactId);
  }
}
