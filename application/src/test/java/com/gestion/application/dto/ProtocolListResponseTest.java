package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class ProtocolListResponseTest {

  @Test
  void testAllArgsConstructorAndGetters() {
    ProtocolListResponse response =
        new ProtocolListResponse(
            101,
            "Protocolo estándar",
            new BigDecimal("1200.50"),
            false,
            "Juan Pérez",
            "600123456",
            "12345678A");

    assertEquals(101, response.getIdProtocol());
    assertEquals("Protocolo estándar", response.getDescription());
    assertEquals(new BigDecimal("1200.50"), response.getPrice());
    assertFalse(response.getIsFinished());
    assertEquals("Juan Pérez", response.getContactName());
    assertEquals("600123456", response.getContactTelephone());
    assertEquals("12345678A", response.getContactNif());
  }
}
