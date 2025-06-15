package com.gestion.application.service.surgery.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.exception.SurgeryReservationNotFoundException;
import com.gestion.application.model.Surgery;
import com.gestion.application.model.SurgeryReservation;
import com.gestion.application.repository.SurgeryRepository;
import com.gestion.application.repository.SurgeryReservationRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class CreateSurgeryImplTest {

  @Mock private SurgeryRepository surgeryRepository;

  @Mock private SurgeryReservationRepository reservationRepository;

  @InjectMocks private CreateSurgeryImpl createSurgeryService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void createSurgery_shouldSaveSurgeryAndConfirmReservation() {
    Integer reservationId = 1;
    LocalDate date = LocalDate.of(2025, 6, 14);
    String observations = "Observation test";

    SurgeryReservation reservation = new SurgeryReservation();
    reservation.setConfirmed(false);

    Surgery savedSurgery = new Surgery();
    savedSurgery.setIdSurgery(10);
    savedSurgery.setDate(date);
    savedSurgery.setObservations(observations);
    savedSurgery.setIsVisible(true);
    savedSurgery.setSurgeryReservation(reservation);

    when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));
    when(reservationRepository.save(reservation)).thenReturn(reservation);
    when(surgeryRepository.save(any(Surgery.class))).thenReturn(savedSurgery);

    Surgery result = createSurgeryService.createSurgery(reservationId, date, observations);

    assertNotNull(result);
    assertEquals(10, result.getIdSurgery());
    assertEquals(date, result.getDate());
    assertEquals(observations, result.getObservations());
    assertTrue(result.getIsVisible());
    assertEquals(reservation, result.getSurgeryReservation());
    assertTrue(reservation.getConfirmed());

    verify(reservationRepository).findById(reservationId);
    verify(reservationRepository).save(reservation);
    verify(surgeryRepository).save(any(Surgery.class));
  }

  @Test
  void createSurgery_shouldThrowException_whenReservationNotFound() {
    Integer reservationId = 999;
    LocalDate date = LocalDate.now();
    String observations = "Obs";

    when(reservationRepository.findById(reservationId)).thenReturn(Optional.empty());

    assertThrows(
        SurgeryReservationNotFoundException.class,
        () -> createSurgeryService.createSurgery(reservationId, date, observations));

    verify(reservationRepository).findById(reservationId);
    verify(reservationRepository, never()).save(any());
    verify(surgeryRepository, never()).save(any());
  }
}
