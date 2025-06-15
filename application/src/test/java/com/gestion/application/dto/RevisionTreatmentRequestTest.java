package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RevisionTreatmentRequestTest {

  @Test
  void testGettersAndSetters() {
    RevisionTreatmentRequest request = new RevisionTreatmentRequest();

    request.setRevisionId(101);
    request.setProductId(202);

    assertEquals(101, request.getRevisionId());
    assertEquals(202, request.getProductId());
  }
}
