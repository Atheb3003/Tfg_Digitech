package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class UpdateProtocolRequestTest {

  @Test
  void testGettersAndSetters() {
    UpdateProtocolRequest request = new UpdateProtocolRequest();

    request.setIdProtocol(101);
    request.setContactId(202);
    request.setDescription("Actualización de protocolo");
    request.setPrice(new BigDecimal("2000.00"));

    List<Integer> productIds = Arrays.asList(1, 2, 3);
    request.setProductIds(productIds);

    assertEquals(101, request.getIdProtocol());
    assertEquals(202, request.getContactId());
    assertEquals("Actualización de protocolo", request.getDescription());
    assertEquals(new BigDecimal("2000.00"), request.getPrice());
    assertEquals(productIds, request.getProductIds());
  }
}
