package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class TransactionSearchDtoTest {

  @Test
  void testGettersAndSetters() {
    TransactionSearchDto dto = new TransactionSearchDto();

    dto.setPatientName("María López");
    dto.setTransactionId(1234);
    dto.setAmount(500.00);
    dto.setPatientDni("98765432B");
    dto.setTelephoneNumber("600112233");
    dto.setContactIdentifier("C1234");
    dto.setTransactionDate(LocalDate.of(2025, 6, 14));

    assertEquals("María López", dto.getPatientName());
    assertEquals(1234, dto.getTransactionId());
    assertEquals(500.00, dto.getAmount());
    assertEquals("98765432B", dto.getPatientDni());
    assertEquals("600112233", dto.getTelephoneNumber());
    assertEquals("C1234", dto.getContactIdentifier());
    assertEquals(LocalDate.of(2025, 6, 14), dto.getTransactionDate());
  }
}
