package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class TransactionSummaryDTOTest {

  @Test
  void testNoArgsConstructorAndSetters() {
    TransactionSummaryDTO dto = new TransactionSummaryDTO();

    dto.setIdTransaction(1001);
    dto.setTransactionDate(LocalDate.of(2025, 6, 14));
    dto.setAmount(350.00);

    dto.setIdPatient(501);
    dto.setFullName("Laura Gómez");
    dto.setIdContact(2002);

    assertEquals(1001, dto.getIdTransaction());
    assertEquals(LocalDate.of(2025, 6, 14), dto.getTransactionDate());
    assertEquals(350.00, dto.getAmount());

    assertEquals(501, dto.getIdPatient());
    assertEquals("Laura Gómez", dto.getFullName());
    assertEquals(2002, dto.getIdContact());
  }
}
