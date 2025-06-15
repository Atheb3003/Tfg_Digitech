package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ProtocolTreatmentRequestTest {

  @Test
  void testGettersAndSetters() {
    ProtocolTreatmentRequest request = new ProtocolTreatmentRequest();

    request.setProtocolId(10);
    request.setProductId(20);
    request.setIsFinished(true);
    request.setIsPaid(false);

    assertEquals(10, request.getProtocolId());
    assertEquals(20, request.getProductId());
    assertTrue(request.getIsFinished());
    assertFalse(request.getIsPaid());
  }
}
