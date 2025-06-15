package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class SurgeryReservationResponseTest {

  @Test
  void testGettersAndSetters() {
    SurgeryReservationResponse response = new SurgeryReservationResponse();

    response.setIdSurgeryReservation(101);
    response.setIdPatient(202);
    response.setIdContact(303);
    response.setIdContactString("C303");
    response.setContactFullName("Laura Gómez");

    response.setDescription("Reserva cirugía avanzada");
    response.setFollicularUnits(1500);
    response.setSurgicalTechnique("FUE");
    response.setEstimatedDate(LocalDate.of(2025, 9, 15));
    response.setNational(true);

    response.setDeposit(new BigDecimal("600.00"));
    response.setSurgeryPrice(new BigDecimal("3500.00"));
    response.setIsVisible(true);
    response.setConfirmed(false);

    response.setRemainingMoney(new BigDecimal("2900.00"));
    response.setIsPaid(true);

    assertEquals(101, response.getIdSurgeryReservation());
    assertEquals(202, response.getIdPatient());
    assertEquals(303, response.getIdContact());
    assertEquals("C303", response.getIdContactString());
    assertEquals("Laura Gómez", response.getContactFullName());

    assertEquals("Reserva cirugía avanzada", response.getDescription());
    assertEquals(1500, response.getFollicularUnits());
    assertEquals("FUE", response.getSurgicalTechnique());
    assertEquals(LocalDate.of(2025, 9, 15), response.getEstimatedDate());
    assertTrue(response.getNational());

    assertEquals(new BigDecimal("600.00"), response.getDeposit());
    assertEquals(new BigDecimal("3500.00"), response.getSurgeryPrice());
    assertTrue(response.getIsVisible());
    assertFalse(response.getConfirmed());

    assertEquals(new BigDecimal("2900.00"), response.getRemainingMoney());
    assertTrue(response.getIsPaid());
  }
}
