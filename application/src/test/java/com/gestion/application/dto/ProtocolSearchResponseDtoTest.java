package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ProtocolSearchResponseDtoTest {

  @Test
  void testGettersAndSetters() {
    ProtocolSearchResponseDto dto = new ProtocolSearchResponseDto();

    dto.setProtocolId(500);
    dto.setProtocolDescription("Búsqueda avanzada");
    dto.setProtocolTotalPrice(new BigDecimal("3200.00"));
    dto.setContactIdString("C500");
    dto.setFinished(false);

    ProtocolSearchTreatmentDto treatment1 = new ProtocolSearchTreatmentDto();

    ProtocolSearchTreatmentDto treatment2 = new ProtocolSearchTreatmentDto();

    List<ProtocolSearchTreatmentDto> treatments = Arrays.asList(treatment1, treatment2);
    dto.setTreatments(treatments);

    assertEquals(500, dto.getProtocolId());
    assertEquals("Búsqueda avanzada", dto.getProtocolDescription());
    assertEquals(new BigDecimal("3200.00"), dto.getProtocolTotalPrice());
    assertEquals("C500", dto.getContactIdString());
    assertFalse(dto.getFinished());
    assertEquals(treatments, dto.getTreatments());
  }
}
