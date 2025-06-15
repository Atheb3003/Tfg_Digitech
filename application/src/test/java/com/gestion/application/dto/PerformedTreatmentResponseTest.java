package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class PerformedTreatmentResponseTest {

  @Test
  void testNoArgsConstructorAndSetters() {
    PerformedTreatmentResponse response = new PerformedTreatmentResponse();

    response.setId(1);
    response.setPerformedDate(LocalDate.of(2025, 6, 14));
    response.setFinalPrice(450.0);
    response.setNotes("Tratamiento exitoso");
    response.setProductName("Producto XYZ");
    response.setIsFromProtocol(true);
    response.setRevisionId(123);

    assertEquals(1, response.getId());
    assertEquals(LocalDate.of(2025, 6, 14), response.getPerformedDate());
    assertEquals(450.0, response.getFinalPrice());
    assertEquals("Tratamiento exitoso", response.getNotes());
    assertEquals("Producto XYZ", response.getProductName());
    assertTrue(response.getIsFromProtocol());
    assertEquals(123, response.getRevisionId());
  }

  @Test
  void testAllArgsConstructor() {
    PerformedTreatmentResponse response =
        new PerformedTreatmentResponse(
            2, LocalDate.of(2025, 7, 1), 500.5, "Otro tratamiento", "Producto ABC", false, 456);

    assertEquals(2, response.getId());
    assertEquals(LocalDate.of(2025, 7, 1), response.getPerformedDate());
    assertEquals(500.5, response.getFinalPrice());
    assertEquals("Otro tratamiento", response.getNotes());
    assertEquals("Producto ABC", response.getProductName());
    assertFalse(response.getIsFromProtocol());
    assertEquals(456, response.getRevisionId());
  }
}
