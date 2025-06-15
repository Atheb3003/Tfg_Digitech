package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ProtocolDetailsDtoTest {

  @Test
  void testGettersAndSetters() {
    ProtocolDetailsDto dto = new ProtocolDetailsDto();

    dto.setIdProtocol(123);
    dto.setProtocolDescription("Protocolo de tratamiento completo");
    dto.setTotalPrice(new BigDecimal("1500.00"));
    dto.setIdContactString("C123");
    dto.setIsFinished(false);

    ProtocolTreatmentInfoDto treatment1 = new ProtocolTreatmentInfoDto();
    treatment1.setPrice(new BigDecimal("500.00"));

    ProtocolTreatmentInfoDto treatment2 = new ProtocolTreatmentInfoDto();

    treatment2.setPrice(new BigDecimal("1000.00"));

    List<ProtocolTreatmentInfoDto> treatments = Arrays.asList(treatment1, treatment2);
    dto.setProtocolTreatments(treatments);

    assertEquals(123, dto.getIdProtocol());
    assertEquals("Protocolo de tratamiento completo", dto.getProtocolDescription());
    assertEquals(new BigDecimal("1500.00"), dto.getTotalPrice());
    assertEquals("C123", dto.getIdContactString());
    assertFalse(dto.getIsFinished());
    assertEquals(treatments, dto.getProtocolTreatments());
  }
}
