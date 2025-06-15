package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RevisionTreatmentResponseTest {

  @Test
  void testGettersAndSetters() {
    RevisionTreatmentResponse response = new RevisionTreatmentResponse();

    response.setId(1);
    response.setRevisionId(100);
    response.setProductId(200);

    assertEquals(1, response.getId());
    assertEquals(100, response.getRevisionId());
    assertEquals(200, response.getProductId());
  }
}
