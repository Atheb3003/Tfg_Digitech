package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class ShippingRequestTest {

  @Test
  void testGettersAndSetters() {
    ShippingRequest request = new ShippingRequest();

    request.setIdContacto(12345L);
    request.setFechaEnvio(LocalDate.of(2025, 6, 14));
    request.setLocalizador("LOC123456");
    request.setPais("España");
    request.setMetodoPago("Tarjeta");
    request.setProductIds(Arrays.asList(1L, 2L, 3L));
    request.setInternacional(true);
    request.setEnviado(false);
    request.setFechaRealEnvio(LocalDate.of(2025, 6, 15));
    request.setIdUnicoPaciente("PAC123");

    assertEquals(12345L, request.getIdContacto());
    assertEquals(LocalDate.of(2025, 6, 14), request.getFechaEnvio());
    assertEquals("LOC123456", request.getLocalizador());
    assertEquals("España", request.getPais());
    assertEquals("Tarjeta", request.getMetodoPago());
    assertEquals(Arrays.asList(1L, 2L, 3L), request.getProductIds());
    assertTrue(request.isInternacional());
    assertFalse(request.isEnviado());
    assertEquals(LocalDate.of(2025, 6, 15), request.getFechaRealEnvio());
    assertEquals("PAC123", request.getIdUnicoPaciente());
  }
}
