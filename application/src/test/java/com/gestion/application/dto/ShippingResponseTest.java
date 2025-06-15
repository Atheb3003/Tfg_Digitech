package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class ShippingResponseTest {

  @Test
  void testGettersAndSetters() {
    ShippingResponse response = new ShippingResponse();

    response.setId(1001L);
    response.setIdContacto(12345L);
    response.setFechaEnvio(LocalDate.of(2025, 6, 14));
    response.setLocalizador("LOC654321");
    response.setPais("Francia");
    response.setMetodoPago("PayPal");
    response.setProductIds(Arrays.asList(5L, 6L, 7L));
    response.setInternacional(false);
    response.setEnviado(true);
    response.setFechaRealEnvio(LocalDate.of(2025, 6, 15));
    response.setIdUnicoPaciente("PAC456");

    assertEquals(1001L, response.getId());
    assertEquals(12345L, response.getIdContacto());
    assertEquals(LocalDate.of(2025, 6, 14), response.getFechaEnvio());
    assertEquals("LOC654321", response.getLocalizador());
    assertEquals("Francia", response.getPais());
    assertEquals("PayPal", response.getMetodoPago());
    assertEquals(Arrays.asList(5L, 6L, 7L), response.getProductIds());
    assertFalse(response.isInternacional());
    assertTrue(response.isEnviado());
    assertEquals(LocalDate.of(2025, 6, 15), response.getFechaRealEnvio());
    assertEquals("PAC456", response.getIdUnicoPaciente());
  }
}
