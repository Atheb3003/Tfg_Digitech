package com.gestion.application.service.patient.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.PatientResponse;
import com.gestion.application.exception.PatientNotFoundException;
import com.gestion.application.mapper.PatientMapper;
import com.gestion.application.model.Patient;
import com.gestion.application.repository.PatientRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class GetPatientByIdImplTest {

  @Mock private PatientRepository patientRepository;

  @Mock private PatientMapper patientMapper;

  @InjectMocks private GetPatientByIdImpl getPatientByIdImpl;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldReturnPatientResponseWhenPatientExists() {
    // Given
    Integer id = 5;
    Patient patient = new Patient();
    patient.setIdPatient(id);

    PatientResponse response = new PatientResponse();
    response.setIdPatient(id);

    when(patientRepository.existsById(id)).thenReturn(true);
    when(patientRepository.findById(id)).thenReturn(Optional.of(patient));
    when(patientMapper.toResponse(patient)).thenReturn(response);

    // When
    PatientResponse result = getPatientByIdImpl.getPatientById(id);

    // Then
    assertNotNull(result);
    assertEquals(id, result.getIdPatient());
    verify(patientRepository).existsById(id);
    verify(patientRepository).findById(id);
    verify(patientMapper).toResponse(patient);
  }

  @Test
  void shouldThrowPatientNotFoundExceptionWhenPatientDoesNotExist() {
    // Given
    Integer id = 99;
    when(patientRepository.existsById(id)).thenReturn(false);

    // Then
    assertThrows(PatientNotFoundException.class, () -> getPatientByIdImpl.getPatientById(id));
    verify(patientRepository).existsById(id);
    verify(patientRepository, never()).findById(any());
    verifyNoInteractions(patientMapper);
  }
}
