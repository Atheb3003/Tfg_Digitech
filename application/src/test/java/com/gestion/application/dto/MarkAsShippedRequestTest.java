package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class MarkAsShippedRequestTest {

  @Test
  void testGettersAndSetters() {
    MarkAsShippedRequest request = new MarkAsShippedRequest();

    request.setEnviado(true);
    request.setFechaRealEnvio(LocalDate.of(2025, 6, 14));

    assertTrue(request.isEnviado());
    assertEquals(LocalDate.of(2025, 6, 14), request.getFechaRealEnvio());
  }
}
