package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class SearchTransactionResponseDTOTest {

  @Test
  void testGettersAndSetters() {
    SearchTransactionResponseDTO dto = new SearchTransactionResponseDTO();

    dto.setIdTransaction(1001);
    dto.setTransactionDate(LocalDate.of(2025, 6, 14));
    dto.setAmount(750.0);
    dto.setDescription("Pago por consulta");

    dto.setIsVisible(true);
    dto.setIdPatient(501);
    dto.setIdContactString("C501");
    dto.setDni("12345678A");
    dto.setContactFullName("Laura Gómez");
    dto.setTelephoneNumber1("600123456");
    dto.setTelephoneNumber2("600654321");

    assertEquals(1001, dto.getIdTransaction());
    assertEquals(LocalDate.of(2025, 6, 14), dto.getTransactionDate());
    assertEquals(750.0, dto.getAmount());
    assertEquals("Pago por consulta", dto.getDescription());

    assertTrue(dto.getIsVisible());
    assertEquals(501, dto.getIdPatient());
    assertEquals("C501", dto.getIdContactString());
    assertEquals("12345678A", dto.getDni());
    assertEquals("Laura Gómez", dto.getContactFullName());
    assertEquals("600123456", dto.getTelephoneNumber1());
    assertEquals("600654321", dto.getTelephoneNumber2());
  }
}
