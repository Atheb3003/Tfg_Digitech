package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class TransactionDetailResponseTest {

  @Test
  void testGettersAndSetters() {
    TransactionDetailResponse response = new TransactionDetailResponse();

    response.setIdDetail(1001);
    response.setTransactionId(2002);
    response.setProductId(3003);
    response.setQuantity(5);
    response.setPrice(new BigDecimal("250.00"));
    response.setProtocolTreatmentId(4004);
    response.setSurgeryReservationId(5005);

    assertEquals(1001, response.getIdDetail());
    assertEquals(2002, response.getTransactionId());
    assertEquals(3003, response.getProductId());
    assertEquals(5, response.getQuantity());
    assertEquals(new BigDecimal("250.00"), response.getPrice());
    assertEquals(4004, response.getProtocolTreatmentId());
    assertEquals(5005, response.getSurgeryReservationId());
  }
}
