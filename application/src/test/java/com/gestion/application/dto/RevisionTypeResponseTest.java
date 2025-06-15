package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RevisionTypeResponseTest {

  @Test
  void testGettersAndSetters() {
    RevisionTypeResponse response = new RevisionTypeResponse();

    response.setIdRevisionType(1);
    response.setTypeName("Revisión Inicial");

    assertEquals(1, response.getIdRevisionType());
    assertEquals("Revisión Inicial", response.getTypeName());
  }
}
