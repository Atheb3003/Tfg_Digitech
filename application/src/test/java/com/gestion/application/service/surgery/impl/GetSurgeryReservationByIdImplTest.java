package com.gestion.application.service.surgery.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.SurgeryReservationResponse;
import com.gestion.application.exception.SurgeryReservationNotFoundException;
import com.gestion.application.model.Contact;
import com.gestion.application.model.Patient;
import com.gestion.application.model.SurgeryReservation;
import com.gestion.application.repository.SurgeryReservationRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class GetSurgeryReservationByIdImplTest {

  @Mock private SurgeryReservationRepository reservationRepository;

  @InjectMocks private GetSurgeryReservationByIdImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getById_shouldReturnMappedResponse_whenFound() {
    Integer id = 1;

    Contact contact = new Contact();
    contact.setIdContact(10);
    contact.setName("Jane");
    contact.setSurname("Doe");

    Patient patient = new Patient();
    patient.setIdPatient(5);
    patient.setContact(contact);

    SurgeryReservation reservation = new SurgeryReservation();
    reservation.setIdSurgeryReservation(id);
    reservation.setPatient(patient);
    reservation.setDescription("Test Desc");
    reservation.setFollicularUnits(120);
    reservation.setSurgicalTechnique("Technique Y");
    reservation.setEstimatedDate(LocalDate.now().plusDays(3));
    reservation.setNational(true);
    reservation.setDeposit(BigDecimal.valueOf(300));
    reservation.setSurgeryPrice(BigDecimal.valueOf(1000));
    reservation.setIsVisible(true);
    reservation.setConfirmed(false);
    reservation.setRemainingMoney(BigDecimal.valueOf(700));
    reservation.setIsPaid(false);

    when(reservationRepository.findById(id)).thenReturn(Optional.of(reservation));

    SurgeryReservationResponse response = service.getById(id);

    assertNotNull(response);
    assertEquals(id, response.getIdSurgeryReservation());
    assertEquals(patient.getIdPatient(), response.getIdPatient());
    assertEquals(contact.getIdContact(), response.getIdContact());
    assertEquals("Jane Doe", response.getContactFullName());
    assertEquals(reservation.getDescription(), response.getDescription());
    assertEquals(reservation.getFollicularUnits(), response.getFollicularUnits());
    assertEquals(reservation.getSurgicalTechnique(), response.getSurgicalTechnique());
    assertEquals(reservation.getEstimatedDate(), response.getEstimatedDate());
    assertEquals(reservation.getNational(), response.getNational());
    assertEquals(reservation.getDeposit(), response.getDeposit());
    assertEquals(reservation.getSurgeryPrice(), response.getSurgeryPrice());
    assertEquals(reservation.getIsVisible(), response.getIsVisible());
    assertEquals(reservation.getConfirmed(), response.getConfirmed());
    assertEquals(reservation.getRemainingMoney(), response.getRemainingMoney());
    assertEquals(reservation.getIsPaid(), response.getIsPaid());

    verify(reservationRepository).findById(id);
  }

  @Test
  void getById_shouldThrowException_whenNotFound() {
    Integer id = 99;

    when(reservationRepository.findById(id)).thenReturn(Optional.empty());

    assertThrows(SurgeryReservationNotFoundException.class, () -> service.getById(id));

    verify(reservationRepository).findById(id);
  }
}
