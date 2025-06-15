package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SuccessfulMarkAsPaidResponseTest {

  @Test
  void testAllArgsConstructorAndGetters() {
    SuccessfulMarkAsPaidResponse response =
        new SuccessfulMarkAsPaidResponse("OK", "Pago registrado correctamente");

    assertEquals("OK", response.getStatus());
    assertEquals("Pago registrado correctamente", response.getMessage());
  }

  @Test
  void testSetters() {
    SuccessfulMarkAsPaidResponse response =
        new SuccessfulMarkAsPaidResponse("PENDING", "Pago en proceso");

    response.setStatus("COMPLETED");
    response.setMessage("Pago completado");

    assertEquals("COMPLETED", response.getStatus());
    assertEquals("Pago completado", response.getMessage());
  }
}
