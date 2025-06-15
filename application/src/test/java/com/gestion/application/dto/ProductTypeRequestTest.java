package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ProductTypeRequestTest {

  @Test
  void testGettersAndSetters() {
    ProductTypeRequest request = new ProductTypeRequest();

    request.setTypeProduct("Medicamento");

    assertEquals("Medicamento", request.getTypeProduct());
  }
}
