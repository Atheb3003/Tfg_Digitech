package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class TransactionDetailRequestTest {

  @Test
  void testGettersAndSetters() {
    TransactionDetailRequest request = new TransactionDetailRequest();

    request.setTransactionId(101);
    request.setProductId(202);
    request.setQuantity(3);
    request.setPrice(new BigDecimal("99.99"));
    request.setProtocolTreatmentId(303);
    request.setSurgeryReservationId(404);

    assertEquals(101, request.getTransactionId());
    assertEquals(202, request.getProductId());
    assertEquals(3, request.getQuantity());
    assertEquals(new BigDecimal("99.99"), request.getPrice());
    assertEquals(303, request.getProtocolTreatmentId());
    assertEquals(404, request.getSurgeryReservationId());
  }
}
