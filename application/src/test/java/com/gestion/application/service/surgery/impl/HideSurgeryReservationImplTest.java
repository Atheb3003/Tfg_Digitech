package com.gestion.application.service.surgery.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.exception.SurgeryReservationNotFoundException;
import com.gestion.application.model.SurgeryReservation;
import com.gestion.application.repository.SurgeryReservationRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class HideSurgeryReservationImplTest {

  @Mock private SurgeryReservationRepository reservationRepository;

  @InjectMocks private HideSurgeryReservationImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void hide_shouldSetIsVisibleFalseAndSave_whenReservationExists() {
    Integer reservationId = 1;
    SurgeryReservation reservation = new SurgeryReservation();
    reservation.setIsVisible(true);

    when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));
    when(reservationRepository.save(reservation)).thenReturn(reservation);

    service.hide(reservationId);

    assertFalse(reservation.getIsVisible());
    verify(reservationRepository).findById(reservationId);
    verify(reservationRepository).save(reservation);
  }

  @Test
  void hide_shouldThrowException_whenReservationNotFound() {
    Integer reservationId = 2;

    when(reservationRepository.findById(reservationId)).thenReturn(Optional.empty());

    assertThrows(SurgeryReservationNotFoundException.class, () -> service.hide(reservationId));

    verify(reservationRepository).findById(reservationId);
    verify(reservationRepository, never()).save(any());
  }
}
