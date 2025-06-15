package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ProductTypeResponseTest {

  @Test
  void testGettersAndSetters() {
    ProductTypeResponse response = new ProductTypeResponse();

    response.setIdProductType(10);
    response.setTypeProduct("Cosmético");

    assertEquals(10, response.getIdProductType());
    assertEquals("Cosmético", response.getTypeProduct());
  }
}
