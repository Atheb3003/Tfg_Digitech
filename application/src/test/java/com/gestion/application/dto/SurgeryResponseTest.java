package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class SurgeryResponseTest {

  @Test
  void testAllArgsConstructorAndGetters() {
    SurgeryResponse response =
        new SurgeryResponse(1, 100, LocalDate.of(2025, 7, 20), "Cirugía exitosa", true);

    assertEquals(1, response.getIdSurgery());
    assertEquals(100, response.getIdSurgeryReservation());
    assertEquals(LocalDate.of(2025, 7, 20), response.getDate());
    assertEquals("Cirugía exitosa", response.getObservations());
    assertTrue(response.getIsVisible());
  }
}
