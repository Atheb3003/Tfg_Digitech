package com.gestion.application.service.surgery.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.exception.SurgeryReservationAlreadyPaidException;
import com.gestion.application.exception.SurgeryReservationNotFoundException;
import com.gestion.application.model.SurgeryReservation;
import com.gestion.application.repository.SurgeryReservationRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class AddPaymentToReservationImplTest {

  @Mock private SurgeryReservationRepository reservationRepository;

  @InjectMocks private AddPaymentToReservationImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void addPayment_shouldAddAmountAndUpdateReservation() {
    Integer reservationId = 1;
    SurgeryReservation reservation = new SurgeryReservation();
    reservation.setDeposit(BigDecimal.valueOf(100));
    reservation.setSurgeryPrice(BigDecimal.valueOf(500));
    reservation.setIsPaid(false);
    reservation.setRemainingMoney(BigDecimal.valueOf(400));

    BigDecimal amountToAdd = BigDecimal.valueOf(150);
    BigDecimal expectedNewDeposit = BigDecimal.valueOf(250);
    BigDecimal expectedRemaining = BigDecimal.valueOf(250);

    when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));
    when(reservationRepository.save(any(SurgeryReservation.class)))
        .thenAnswer(i -> i.getArgument(0));

    SurgeryReservation updated = service.addPayment(reservationId, amountToAdd);

    assertEquals(expectedNewDeposit, updated.getDeposit());
    assertEquals(expectedRemaining, updated.getRemainingMoney());
    assertFalse(updated.getIsPaid());

    verify(reservationRepository).findById(reservationId);
    verify(reservationRepository).save(reservation);
  }

  @Test
  void addPayment_shouldSetIsPaidTrueWhenFullyPaid() {
    Integer reservationId = 1;
    SurgeryReservation reservation = new SurgeryReservation();
    reservation.setDeposit(BigDecimal.valueOf(400));
    reservation.setSurgeryPrice(BigDecimal.valueOf(500));
    reservation.setIsPaid(false);
    reservation.setRemainingMoney(BigDecimal.valueOf(100));

    BigDecimal amountToAdd = BigDecimal.valueOf(150);

    when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));
    when(reservationRepository.save(any(SurgeryReservation.class)))
        .thenAnswer(i -> i.getArgument(0));

    SurgeryReservation updated = service.addPayment(reservationId, amountToAdd);

    assertEquals(BigDecimal.valueOf(550), updated.getDeposit());
    assertEquals(BigDecimal.valueOf(-50), updated.getRemainingMoney());
    assertTrue(updated.getIsPaid());

    verify(reservationRepository).findById(reservationId);
    verify(reservationRepository).save(reservation);
  }

  @Test
  void addPayment_shouldThrowExceptionIfAlreadyPaid() {
    Integer reservationId = 1;
    SurgeryReservation reservation = new SurgeryReservation();
    reservation.setIsPaid(true);

    when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));

    assertThrows(
        SurgeryReservationAlreadyPaidException.class,
        () -> service.addPayment(reservationId, BigDecimal.valueOf(100)));

    verify(reservationRepository).findById(reservationId);
    verify(reservationRepository, never()).save(any());
  }

  @Test
  void addPayment_shouldThrowExceptionIfNotFound() {
    Integer reservationId = 1;

    when(reservationRepository.findById(reservationId)).thenReturn(Optional.empty());

    assertThrows(
        SurgeryReservationNotFoundException.class,
        () -> service.addPayment(reservationId, BigDecimal.valueOf(100)));

    verify(reservationRepository).findById(reservationId);
    verify(reservationRepository, never()).save(any());
  }
}
