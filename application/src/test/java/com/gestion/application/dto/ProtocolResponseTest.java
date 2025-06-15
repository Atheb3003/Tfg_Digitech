package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ProtocolResponseTest {

  @Test
  void testGettersAndSetters() {
    ProtocolResponse response = new ProtocolResponse();

    response.setIdProtocol(321);
    response.setDescription("Protocolo avanzado");
    response.setPrice(new BigDecimal("2500.00"));
    response.setIsFinished(true);

    ProtocolTreatmentResponse treatment1 = new ProtocolTreatmentResponse();
    treatment1.setId(1);

    treatment1.setPrice(new BigDecimal("1000.00"));

    ProtocolTreatmentResponse treatment2 = new ProtocolTreatmentResponse();
    treatment2.setId(2);

    treatment2.setPrice(new BigDecimal("1500.00"));

    List<ProtocolTreatmentResponse> treatments = Arrays.asList(treatment1, treatment2);
    response.setTreatments(treatments);

    assertEquals(321, response.getIdProtocol());
    assertEquals("Protocolo avanzado", response.getDescription());
    assertEquals(new BigDecimal("2500.00"), response.getPrice());
    assertTrue(response.getIsFinished());
    assertEquals(treatments, response.getTreatments());
  }
}
