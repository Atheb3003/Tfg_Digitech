package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class CreateSurgeryRequestTest {

  @Test
  void testGettersAndSetters() {
    CreateSurgeryRequest request = new CreateSurgeryRequest();

    request.setReservationId(77);
    request.setDate(LocalDate.of(2025, 8, 15));
    request.setObservations("Preparación para cirugía");

    assertEquals(77, request.getReservationId());
    assertEquals(LocalDate.of(2025, 8, 15), request.getDate());
    assertEquals("Preparación para cirugía", request.getObservations());
  }
}
