package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ErrorDetailsTest {

  @Test
  void testRecordConstructorAndGetters() {
    ErrorDetails error =
        new ErrorDetails(404, "Not Found", "El recurso no existe", "/api/resource");

    assertEquals(404, error.status());
    assertEquals("Not Found", error.error());
    assertEquals("El recurso no existe", error.message());
    assertEquals("/api/resource", error.path());
  }
}
