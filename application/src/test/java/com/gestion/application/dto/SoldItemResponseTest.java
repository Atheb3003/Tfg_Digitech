package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class SoldItemResponseTest {

  @Test
  void testGettersAndSetters() {
    SoldItemResponse response = new SoldItemResponse();

    response.setType("Producto");
    response.setName("Producto A");
    response.setTransactionId(1001);

    response.setAmount(250.75);
    response.setIdContactString("C1001");
    response.setTransactionDate(LocalDate.of(2025, 6, 14));

    assertEquals("Producto", response.getType());
    assertEquals("Producto A", response.getName());
    assertEquals(1001, response.getTransactionId());

    assertEquals(250.75, response.getAmount());
    assertEquals("C1001", response.getIdContactString());
    assertEquals(LocalDate.of(2025, 6, 14), response.getTransactionDate());
  }
}
