package com.gestion.application.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.gestion.application.dto.ShippingRequest;
import com.gestion.application.dto.ShippingResponse;
import com.gestion.application.model.Shipping;
import java.time.LocalDate;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class ShippingMapperTest {

  @Test
  void testToEntity() {
    ShippingRequest request = new ShippingRequest();
    request.setIdContacto(12345L);
    request.setFechaEnvio(LocalDate.of(2025, 6, 14));
    request.setLocalizador("LOC123");
    request.setPais("España");
    request.setMetodoPago("Tarjeta");
    request.setProductIds(Arrays.asList(1L, 2L, 3L));
    request.setInternacional(true);
    request.setEnviado(false);
    request.setFechaRealEnvio(LocalDate.of(2025, 6, 15));
    request.setIdUnicoPaciente("PAC123");

    Shipping entity = ShippingMapper.toEntity(request);

    assertEquals(12345L, entity.getIdContacto());
    assertEquals(LocalDate.of(2025, 6, 14), entity.getFechaEnvio());
    assertEquals("LOC123", entity.getLocalizador());
    assertEquals("España", entity.getPais());
    assertEquals("Tarjeta", entity.getMetodoPago());
    assertEquals(Arrays.asList(1L, 2L, 3L), entity.getProductIds());
    assertTrue(entity.isInternacional());
    assertFalse(entity.isEnviado());
    assertEquals(LocalDate.of(2025, 6, 15), entity.getFechaRealEnvio());
    assertEquals("PAC123", entity.getIdUnicoPaciente());
  }

  @Test
  void testToResponse() {
    Shipping shipping = new Shipping();
    shipping.setId(1001L);
    shipping.setIdContacto(12345L);
    shipping.setFechaEnvio(LocalDate.of(2025, 6, 14));
    shipping.setLocalizador("LOC456");
    shipping.setPais("Francia");
    shipping.setMetodoPago("PayPal");
    shipping.setProductIds(Arrays.asList(4L, 5L, 6L));
    shipping.setInternacional(false);
    shipping.setEnviado(true);
    shipping.setFechaRealEnvio(LocalDate.of(2025, 6, 16));
    shipping.setIdUnicoPaciente("PAC456");

    ShippingResponse response = ShippingMapper.toResponse(shipping);

    assertEquals(1001L, response.getId());
    assertEquals(12345L, response.getIdContacto());
    assertEquals(LocalDate.of(2025, 6, 14), response.getFechaEnvio());
    assertEquals("LOC456", response.getLocalizador());
    assertEquals("Francia", response.getPais());
    assertEquals("PayPal", response.getMetodoPago());
    assertEquals(Arrays.asList(4L, 5L, 6L), response.getProductIds());
    assertFalse(response.isInternacional());
    assertTrue(response.isEnviado());
    assertEquals(LocalDate.of(2025, 6, 16), response.getFechaRealEnvio());
    assertEquals("PAC456", response.getIdUnicoPaciente());
  }
}
