package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class ProtocolSearchTreatmentDtoTest {

  @Test
  void testGettersAndSetters() {
    ProtocolSearchTreatmentDto dto = new ProtocolSearchTreatmentDto();

    dto.setTreatmentId(101);
    dto.setTreatmentPrice(new BigDecimal("750.00"));
    dto.setProductName("Tratamiento Especial");
    dto.setPaid(true);
    dto.setComplete(false);

    assertEquals(101, dto.getTreatmentId());
    assertEquals(new BigDecimal("750.00"), dto.getTreatmentPrice());
    assertEquals("Tratamiento Especial", dto.getProductName());
    assertTrue(dto.getPaid());
    assertFalse(dto.getComplete());
  }
}
