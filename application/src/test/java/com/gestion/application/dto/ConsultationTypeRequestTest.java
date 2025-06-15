package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ConsultationTypeRequestTest {

  @Test
  void testGettersAndSetters() {
    ConsultationTypeRequest request = new ConsultationTypeRequest();

    request.setTypeName("Consulta Especial");

    assertEquals("Consulta Especial", request.getTypeName());
  }
}
