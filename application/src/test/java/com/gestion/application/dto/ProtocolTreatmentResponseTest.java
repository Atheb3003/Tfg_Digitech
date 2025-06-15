package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class ProtocolTreatmentResponseTest {

  @Test
  void testNoArgsConstructorAndSetters() {
    ProtocolTreatmentResponse response = new ProtocolTreatmentResponse();

    response.setId(1);
    response.setProductId(100);
    response.setProductName("Tratamiento Avanzado");
    response.setPrice(new BigDecimal("1200.00"));
    response.setIsPaid(true);
    response.setIsFinished(false);
    response.setDetail("Detalle del tratamiento");

    assertEquals(1, response.getId());
    assertEquals(100, response.getProductId());
    assertEquals("Tratamiento Avanzado", response.getProductName());
    assertEquals(new BigDecimal("1200.00"), response.getPrice());
    assertTrue(response.getIsPaid());
    assertFalse(response.getIsFinished());
    assertEquals("Detalle del tratamiento", response.getDetail());
  }

  @Test
  void testAllArgsConstructor() {
    ProtocolTreatmentResponse response =
        new ProtocolTreatmentResponse(
            2,
            200,
            "Tratamiento Básico",
            new BigDecimal("800.00"),
            false,
            true,
            "Sin detalles adicionales");

    assertEquals(2, response.getId());
    assertEquals(200, response.getProductId());
    assertEquals("Tratamiento Básico", response.getProductName());
    assertEquals(new BigDecimal("800.00"), response.getPrice());
    assertFalse(response.getIsPaid());
    assertTrue(response.getIsFinished());
    assertEquals("Sin detalles adicionales", response.getDetail());
  }
}
