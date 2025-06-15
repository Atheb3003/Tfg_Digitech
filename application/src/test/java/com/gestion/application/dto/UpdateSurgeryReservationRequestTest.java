package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class UpdateSurgeryReservationRequestTest {

  @Test
  void testGettersAndSetters() {
    UpdateSurgeryReservationRequest request = new UpdateSurgeryReservationRequest();

    request.setDescription("Actualización de cirugía");
    request.setFollicularUnits(1600);
    request.setSurgicalTechnique("FUE");
    request.setEstimatedDate(LocalDate.of(2025, 10, 5));
    request.setNational(false);
    request.setDeposit(new BigDecimal("750.00"));
    request.setSurgeryPrice(new BigDecimal("4000.00"));
    request.setIsVisible(true);

    assertEquals("Actualización de cirugía", request.getDescription());
    assertEquals(1600, request.getFollicularUnits());
    assertEquals("FUE", request.getSurgicalTechnique());
    assertEquals(LocalDate.of(2025, 10, 5), request.getEstimatedDate());
    assertFalse(request.getNational());
    assertEquals(new BigDecimal("750.00"), request.getDeposit());
    assertEquals(new BigDecimal("4000.00"), request.getSurgeryPrice());
    assertTrue(request.getIsVisible());
  }
}
