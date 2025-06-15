package com.gestion.application.service.surgery.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.SurgeryReservationResponse;
import com.gestion.application.exception.PatientNotFoundException;
import com.gestion.application.model.Contact;
import com.gestion.application.model.Patient;
import com.gestion.application.model.SurgeryReservation;
import com.gestion.application.repository.PatientRepository;
import com.gestion.application.repository.SurgeryReservationRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class GetSurgeryReservationsByPatientIdImplTest {

  @Mock private SurgeryReservationRepository reservationRepository;

  @Mock private PatientRepository patientRepository;

  @InjectMocks private GetSurgeryReservationsByPatientIdImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getByPatientId_shouldReturnListOfResponses_whenPatientExists() {
    Integer patientId = 1;

    Contact contact = new Contact();
    contact.setIdContact(50);
    contact.setName("Mark");
    contact.setSurname("Twain");

    Patient patient = new Patient();
    patient.setIdPatient(patientId);
    patient.setContact(contact);

    SurgeryReservation reservation1 = new SurgeryReservation();
    reservation1.setIdSurgeryReservation(101);
    reservation1.setDescription("Desc1");
    reservation1.setFollicularUnits(80);
    reservation1.setSurgicalTechnique("Technique1");
    reservation1.setEstimatedDate(LocalDate.now().plusDays(2));
    reservation1.setNational(true);
    reservation1.setDeposit(BigDecimal.valueOf(150));
    reservation1.setSurgeryPrice(BigDecimal.valueOf(450));
    reservation1.setIsVisible(true);
    reservation1.setPatient(patient);
    reservation1.setConfirmed(true);
    reservation1.setRemainingMoney(BigDecimal.valueOf(300));
    reservation1.setIsPaid(false);

    SurgeryReservation reservation2 = new SurgeryReservation();
    reservation2.setIdSurgeryReservation(102);
    reservation2.setDescription("Desc2");
    reservation2.setFollicularUnits(100);
    reservation2.setSurgicalTechnique("Technique2");
    reservation2.setEstimatedDate(LocalDate.now().plusDays(5));
    reservation2.setNational(false);
    reservation2.setDeposit(BigDecimal.valueOf(200));
    reservation2.setSurgeryPrice(BigDecimal.valueOf(500));
    reservation2.setIsVisible(false);
    reservation2.setPatient(patient);
    reservation2.setConfirmed(false);
    reservation2.setRemainingMoney(BigDecimal.valueOf(300));
    reservation2.setIsPaid(false);

    when(patientRepository.existsById(patientId)).thenReturn(true);
    when(reservationRepository.findByPatient_IdPatient(patientId))
        .thenReturn(List.of(reservation1, reservation2));

    List<SurgeryReservationResponse> responses = service.getByPatientId(patientId);

    assertNotNull(responses);
    assertEquals(2, responses.size());

    SurgeryReservationResponse dto1 = responses.get(0);
    assertEquals(101, dto1.getIdSurgeryReservation());
    assertEquals(patientId, dto1.getIdPatient());
    assertEquals(contact.getIdContact(), dto1.getIdContact());
    assertEquals("Mark Twain", dto1.getContactFullName());
    assertEquals("Desc1", dto1.getDescription());

    SurgeryReservationResponse dto2 = responses.get(1);
    assertEquals(102, dto2.getIdSurgeryReservation());
    assertFalse(dto2.getIsVisible());

    verify(patientRepository).existsById(patientId);
    verify(reservationRepository).findByPatient_IdPatient(patientId);
  }

  @Test
  void getByPatientId_shouldThrowException_whenPatientDoesNotExist() {
    Integer patientId = 999;

    when(patientRepository.existsById(patientId)).thenReturn(false);

    assertThrows(PatientNotFoundException.class, () -> service.getByPatientId(patientId));

    verify(patientRepository).existsById(patientId);
    verify(reservationRepository, never()).findByPatient_IdPatient(any());
  }
}
