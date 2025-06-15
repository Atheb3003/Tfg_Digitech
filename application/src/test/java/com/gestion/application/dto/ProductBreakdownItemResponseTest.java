package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ProductBreakdownItemResponseTest {

  @Test
  void testGettersAndSetters() {
    ProductBreakdownItemResponse response = new ProductBreakdownItemResponse();

    response.setType("Medicamento");
    response.setName("Paracetamol");
    response.setQuantity(50);
    response.setIncome("500.00");

    assertEquals("Medicamento", response.getType());
    assertEquals("Paracetamol", response.getName());
    assertEquals(50, response.getQuantity());
    assertEquals("500.00", response.getIncome());
  }
}
