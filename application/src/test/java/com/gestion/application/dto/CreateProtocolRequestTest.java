package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CreateProtocolRequestTest {

  @Test
  void testGettersAndSetters() {
    CreateProtocolRequest request = new CreateProtocolRequest();

    request.setContactId(55);
    request.setDescription("Protocolo de tratamiento");
    request.setPrice(new BigDecimal("999.99"));
    List<Integer> productIds = Arrays.asList(1, 2, 3, 4);
    request.setProductIds(productIds);

    assertEquals(55, request.getContactId());
    assertEquals("Protocolo de tratamiento", request.getDescription());
    assertEquals(new BigDecimal("999.99"), request.getPrice());
    assertEquals(productIds, request.getProductIds());
  }
}
