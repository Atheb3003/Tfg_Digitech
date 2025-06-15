package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class SurgeryReservationRequestTest {

  @Test
  void testGettersAndSetters() {
    SurgeryReservationRequest request = new SurgeryReservationRequest();

    request.setDescription("Reserva cirugía capilar");
    request.setFollicularUnits(1500);
    request.setSurgicalTechnique("FUE");
    request.setEstimatedDate(LocalDate.of(2025, 9, 10));
    request.setNational(true);
    request.setDeposit(new BigDecimal("500.00"));
    request.setSurgeryPrice(new BigDecimal("3000.00"));
    request.setIsVisible(true);
    request.setConfirmed(false);
    request.setPatientId(88);

    assertEquals("Reserva cirugía capilar", request.getDescription());
    assertEquals(1500, request.getFollicularUnits());
    assertEquals("FUE", request.getSurgicalTechnique());
    assertEquals(LocalDate.of(2025, 9, 10), request.getEstimatedDate());
    assertTrue(request.getNational());
    assertEquals(new BigDecimal("500.00"), request.getDeposit());
    assertEquals(new BigDecimal("3000.00"), request.getSurgeryPrice());
    assertTrue(request.getIsVisible());
    assertFalse(request.getConfirmed());
    assertEquals(88, request.getPatientId());
  }
}
