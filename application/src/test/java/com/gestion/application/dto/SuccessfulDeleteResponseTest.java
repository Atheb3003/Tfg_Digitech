package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SuccessfulDeleteResponseTest {

  @Test
  void testAllArgsConstructorAndGetters() {
    SuccessfulDeleteResponse response = new SuccessfulDeleteResponse("OK", "Eliminación exitosa");

    assertEquals("OK", response.getStatus());
    assertEquals("Eliminación exitosa", response.getMessage());
  }

  @Test
  void testSetters() {
    SuccessfulDeleteResponse response = new SuccessfulDeleteResponse("PENDING", "En proceso");

    response.setStatus("COMPLETED");
    response.setMessage("Eliminación completada");

    assertEquals("COMPLETED", response.getStatus());
    assertEquals("Eliminación completada", response.getMessage());
  }
}
