package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class FullDetailSurgeryResponseTest {

  @Test
  void testGettersAndSetters() {
    FullDetailSurgeryResponse response = new FullDetailSurgeryResponse();

    response.setIdSurgery(101);
    response.setIdReservation(202);
    response.setDate(LocalDate.of(2025, 10, 20));
    response.setObservations("Cirugía exitosa");
    response.setIsVisible(true);

    response.setIdPatient(303);
    response.setIdContact(404);
    response.setIdContactString("C404");
    response.setContactFullName("Juan Pérez");

    response.setFollicularUnits(1200);
    response.setSurgicalTechnique("FUE");
    response.setNational(true);
    response.setDeposit(new BigDecimal("700.00"));
    response.setSurgeryPrice(new BigDecimal("3500.00"));
    response.setRemainingMoney(new BigDecimal("2800.00"));

    assertEquals(101, response.getIdSurgery());
    assertEquals(202, response.getIdReservation());
    assertEquals(LocalDate.of(2025, 10, 20), response.getDate());
    assertEquals("Cirugía exitosa", response.getObservations());
    assertTrue(response.getIsVisible());

    assertEquals(303, response.getIdPatient());
    assertEquals(404, response.getIdContact());
    assertEquals("C404", response.getIdContactString());
    assertEquals("Juan Pérez", response.getContactFullName());

    assertEquals(1200, response.getFollicularUnits());
    assertEquals("FUE", response.getSurgicalTechnique());
    assertTrue(response.getNational());
    assertEquals(new BigDecimal("700.00"), response.getDeposit());
    assertEquals(new BigDecimal("3500.00"), response.getSurgeryPrice());
    assertEquals(new BigDecimal("2800.00"), response.getRemainingMoney());
  }
}
