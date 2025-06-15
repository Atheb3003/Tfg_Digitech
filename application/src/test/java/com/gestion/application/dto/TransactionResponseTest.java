package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class TransactionResponseTest {

  @Test
  void testNoArgsConstructorAndSetters() {
    TransactionResponse response = new TransactionResponse();

    response.setIdTransaction(1001);
    response.setTransactionDate(LocalDate.of(2025, 6, 14));
    response.setAmount(300.50);
    response.setDescription("Pago consulta");

    response.setIsVisible(true);
    response.setIdPatient(501);
    response.setIdContactString("C501");

    assertEquals(1001, response.getIdTransaction());
    assertEquals(LocalDate.of(2025, 6, 14), response.getTransactionDate());
    assertEquals(300.50, response.getAmount());
    assertEquals("Pago consulta", response.getDescription());
    assertTrue(response.getIsVisible());
    assertEquals(501, response.getIdPatient());
    assertEquals("C501", response.getIdContactString());
  }
}
