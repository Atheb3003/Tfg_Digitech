package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class TransactionRequestTest {

  @Test
  void testGettersAndSetters() {
    TransactionRequest request = new TransactionRequest();

    request.setTransactionDate(LocalDate.of(2025, 6, 14));
    request.setAmount(150.75);
    request.setDescription("Pago tratamiento");

    request.setIdPatient(501);

    assertEquals(LocalDate.of(2025, 6, 14), request.getTransactionDate());
    assertEquals(150.75, request.getAmount());
    assertEquals("Pago tratamiento", request.getDescription());

    assertEquals(501, request.getIdPatient());
  }
}
