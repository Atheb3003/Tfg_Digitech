package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class AddTreatmentToProtocolRequestTest {

  @Test
  void testGettersAndSetters() {
    AddTreatmentToProtocolRequest request = new AddTreatmentToProtocolRequest();

    request.setProtocolId(10);
    request.setProductId(20);
    request.setDetail("Tratamiento especial");
    request.setPrice(new BigDecimal("250.00"));

    assertEquals(10, request.getProtocolId());
    assertEquals(20, request.getProductId());
    assertEquals("Tratamiento especial", request.getDetail());
    assertEquals(new BigDecimal("250.00"), request.getPrice());
  }
}
