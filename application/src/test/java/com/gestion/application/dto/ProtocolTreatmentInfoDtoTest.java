package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class ProtocolTreatmentInfoDtoTest {

  @Test
  void testGettersAndSetters() {
    ProtocolTreatmentInfoDto dto = new ProtocolTreatmentInfoDto();

    dto.setId(1);
    dto.setPrice(new BigDecimal("1200.00"));
    dto.setProductName("Tratamiento Premium");
    dto.setIsPaid(true);

    assertEquals(1, dto.getId());
    assertEquals(new BigDecimal("1200.00"), dto.getPrice());
    assertEquals("Tratamiento Premium", dto.getProductName());
    assertTrue(dto.getIsPaid());
  }
}
