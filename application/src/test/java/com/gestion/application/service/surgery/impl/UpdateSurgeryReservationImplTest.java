package com.gestion.application.service.surgery.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.SurgeryReservationResponse;
import com.gestion.application.dto.UpdateSurgeryReservationRequest;
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

class UpdateSurgeryReservationImplTest {

  @Mock private SurgeryReservationRepository reservationRepository;

  @InjectMocks private UpdateSurgeryReservationImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void update_shouldModifyReservationAndReturnDto() {
    Integer id = 1;

    SurgeryReservation reservation = new SurgeryReservation();
    reservation.setIdSurgeryReservation(id);
    reservation.setDeposit(BigDecimal.valueOf(100));
    reservation.setSurgeryPrice(BigDecimal.valueOf(500));
    reservation.setRemainingMoney(BigDecimal.valueOf(400));
    reservation.setIsPaid(false);
    reservation.setConfirmed(true);
    reservation.setIsVisible(true);

    Patient patient = new Patient();
    patient.setIdPatient(10);

    Contact contact = new Contact();
    contact.setName("Alice");
    contact.setSurname("Wonderland");

    patient.setContact(contact);
    reservation.setPatient(patient);

    UpdateSurgeryReservationRequest request = new UpdateSurgeryReservationRequest();
    request.setDescription("Updated description");
    request.setFollicularUnits(200);
    request.setSurgicalTechnique("Updated technique");
    request.setEstimatedDate(LocalDate.now().plusDays(10));
    request.setNational(true);
    request.setDeposit(BigDecimal.valueOf(300));
    request.setSurgeryPrice(BigDecimal.valueOf(300));
    request.setIsVisible(false);

    when(reservationRepository.findById(id)).thenReturn(Optional.of(reservation));
    when(reservationRepository.save(reservation)).thenReturn(reservation);

    SurgeryReservationResponse dto = service.update(id, request);

    assertNotNull(dto);
    assertEquals(id, dto.getIdSurgeryReservation());
    assertEquals(request.getDescription(), dto.getDescription());
    assertEquals(request.getFollicularUnits(), dto.getFollicularUnits());
    assertEquals(request.getSurgicalTechnique(), dto.getSurgicalTechnique());
    assertEquals(request.getEstimatedDate(), dto.getEstimatedDate());
    assertEquals(request.getNational(), dto.getNational());
    assertEquals(request.getDeposit(), dto.getDeposit());
    assertEquals(request.getSurgeryPrice(), dto.getSurgeryPrice());
    assertEquals(request.getIsVisible(), dto.getIsVisible());
    assertEquals(patient.getIdPatient(), dto.getIdPatient());
    assertEquals("Alice Wonderland", dto.getContactFullName());
    assertEquals(reservation.getConfirmed(), dto.getConfirmed());
    assertEquals(BigDecimal.ZERO, dto.getRemainingMoney());
    assertTrue(dto.getIsPaid());

    verify(reservationRepository).findById(id);
    verify(reservationRepository).save(reservation);
  }

  @Test
  void update_shouldThrowException_whenReservationNotFound() {
    Integer id = 99;
    UpdateSurgeryReservationRequest request = new UpdateSurgeryReservationRequest();

    when(reservationRepository.findById(id)).thenReturn(Optional.empty());

    assertThrows(SurgeryReservationNotFoundException.class, () -> service.update(id, request));

    verify(reservationRepository).findById(id);
    verify(reservationRepository, never()).save(any());
  }
}
