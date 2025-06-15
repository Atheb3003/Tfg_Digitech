package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ConsultationTypeResponseTest {

  @Test
  void testGettersAndSetters() {
    ConsultationTypeResponse response = new ConsultationTypeResponse();

    response.setIdType(7);
    response.setTypeName("Consulta avanzada");

    assertEquals(7, response.getIdType());
    assertEquals("Consulta avanzada", response.getTypeName());
  }
}
