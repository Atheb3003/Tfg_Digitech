package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class AddPaymentRequestTest {

  @Test
  void testGettersAndSetters() {
    AddPaymentRequest request = new AddPaymentRequest();

    BigDecimal amount = new BigDecimal("150.75");
    request.setAmount(amount);

    assertEquals(amount, request.getAmount());
  }
}
