package com.gestion.application.service.surgery.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.CreateSurgeryReservationRequest;
import com.gestion.application.exception.PatientNotFoundException;
import com.gestion.application.model.Patient;
import com.gestion.application.model.SurgeryReservation;
import com.gestion.application.repository.PatientRepository;
import com.gestion.application.repository.SurgeryReservationRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class CreateSurgeryReservationImplTest {

  @Mock private SurgeryReservationRepository surgeryReservationRepository;

  @Mock private PatientRepository patientRepository;

  @InjectMocks private CreateSurgeryReservationImpl createSurgeryReservation;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void create_shouldSaveReservationWithCorrectRemainingAndIsPaid() {
    CreateSurgeryReservationRequest request = new CreateSurgeryReservationRequest();
    request.setIdPatient(1);
    request.setDescription("Test description");
    request.setFollicularUnits(100);
    request.setSurgicalTechnique("Technique A");
    request.setEstimatedDate(LocalDate.now().plusDays(10));
    request.setNational(true);
    request.setDeposit(BigDecimal.valueOf(200));
    request.setSurgeryPrice(BigDecimal.valueOf(500));
    request.setIsVisible(true);

    Patient patient = new Patient();

    SurgeryReservation savedReservation = new SurgeryReservation();
    savedReservation.setIdSurgeryReservation(1);
    savedReservation.setPatient(patient);
    savedReservation.setDescription(request.getDescription());
    savedReservation.setDeposit(request.getDeposit());
    savedReservation.setSurgeryPrice(request.getSurgeryPrice());
    savedReservation.setRemainingMoney(request.getSurgeryPrice().subtract(request.getDeposit()));
    savedReservation.setIsPaid(false);
    savedReservation.setIsVisible(request.getIsVisible());

    when(patientRepository.findById(request.getIdPatient())).thenReturn(Optional.of(patient));
    when(surgeryReservationRepository.save(any(SurgeryReservation.class)))
        .thenReturn(savedReservation);

    SurgeryReservation result = createSurgeryReservation.create(request);

    assertNotNull(result);
    assertEquals(request.getDescription(), result.getDescription());
    assertEquals(request.getDeposit(), result.getDeposit());
    assertEquals(request.getSurgeryPrice(), result.getSurgeryPrice());
    assertEquals(
        request.getSurgeryPrice().subtract(request.getDeposit()), result.getRemainingMoney());
    assertFalse(result.getIsPaid());
    assertTrue(result.getIsVisible());
    assertEquals(patient, result.getPatient());

    verify(patientRepository).findById(request.getIdPatient());
    verify(surgeryReservationRepository).save(any(SurgeryReservation.class));
  }

  @Test
  void create_shouldSetIsPaidTrueWhenDepositCoversPrice() {
    CreateSurgeryReservationRequest request = new CreateSurgeryReservationRequest();
    request.setIdPatient(1);
    request.setDescription("Full payment");
    request.setFollicularUnits(50);
    request.setSurgicalTechnique("Technique B");
    request.setEstimatedDate(LocalDate.now().plusDays(5));
    request.setNational(false);
    request.setDeposit(BigDecimal.valueOf(600));
    request.setSurgeryPrice(BigDecimal.valueOf(500));
    request.setIsVisible(true);

    Patient patient = new Patient();

    SurgeryReservation savedReservation = new SurgeryReservation();
    savedReservation.setIdSurgeryReservation(2);
    savedReservation.setPatient(patient);
    savedReservation.setDescription(request.getDescription());
    savedReservation.setDeposit(request.getDeposit());
    savedReservation.setSurgeryPrice(request.getSurgeryPrice());
    savedReservation.setRemainingMoney(request.getSurgeryPrice().subtract(request.getDeposit()));
    savedReservation.setIsPaid(true);
    savedReservation.setIsVisible(request.getIsVisible());

    when(patientRepository.findById(request.getIdPatient())).thenReturn(Optional.of(patient));
    when(surgeryReservationRepository.save(any(SurgeryReservation.class)))
        .thenReturn(savedReservation);

    SurgeryReservation result = createSurgeryReservation.create(request);

    assertTrue(result.getIsPaid());
    verify(patientRepository).findById(request.getIdPatient());
    verify(surgeryReservationRepository).save(any(SurgeryReservation.class));
  }

  @Test
  void create_shouldThrowExceptionWhenPatientNotFound() {
    CreateSurgeryReservationRequest request = new CreateSurgeryReservationRequest();
    request.setIdPatient(999);

    when(patientRepository.findById(request.getIdPatient())).thenReturn(Optional.empty());

    assertThrows(PatientNotFoundException.class, () -> createSurgeryReservation.create(request));

    verify(patientRepository).findById(request.getIdPatient());
    verify(surgeryReservationRepository, never()).save(any());
  }
}
