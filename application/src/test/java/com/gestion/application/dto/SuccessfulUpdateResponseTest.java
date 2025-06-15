package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SuccessfulUpdateResponseTest {

  @Test
  void testAllArgsConstructorAndGetters() {
    SuccessfulUpdateResponse response =
        new SuccessfulUpdateResponse("OK", "Actualización realizada con éxito");

    assertEquals("OK", response.getStatus());
    assertEquals("Actualización realizada con éxito", response.getMessage());
  }

  @Test
  void testSetters() {
    SuccessfulUpdateResponse response =
        new SuccessfulUpdateResponse("PENDING", "Actualización en proceso");

    response.setStatus("COMPLETED");
    response.setMessage("Actualización completada");

    assertEquals("COMPLETED", response.getStatus());
    assertEquals("Actualización completada", response.getMessage());
  }
}
