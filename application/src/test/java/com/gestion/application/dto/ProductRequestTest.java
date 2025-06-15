package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ProductRequestTest {

  @Test
  void testGettersAndSetters() {
    ProductRequest request = new ProductRequest();

    request.setName("Producto A");
    request.setDescription("Descripción del producto A");
    request.setPrice(99.99);
    request.setIdProductType(3);

    assertEquals("Producto A", request.getName());
    assertEquals("Descripción del producto A", request.getDescription());
    assertEquals(99.99, request.getPrice());
    assertEquals(3, request.getIdProductType());
  }
}
