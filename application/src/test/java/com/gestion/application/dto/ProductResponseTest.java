package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class ProductResponseTest {

  @Test
  void testGettersAndSetters() {
    ProductResponse response = new ProductResponse();

    response.setIdProduct(101);
    response.setName("Producto X");
    response.setDescription("Descripción del producto X");
    response.setPrice(120.50);
    response.setCreationDate(LocalDate.of(2025, 5, 20));
    response.setUpdateDate(LocalDate.of(2025, 6, 10));
    response.setIsVisible(true);
    response.setProductTypeId(5);
    response.setProductType("Categoría A");

    assertEquals(101, response.getIdProduct());
    assertEquals("Producto X", response.getName());
    assertEquals("Descripción del producto X", response.getDescription());
    assertEquals(120.50, response.getPrice());
    assertEquals(LocalDate.of(2025, 5, 20), response.getCreationDate());
    assertEquals(LocalDate.of(2025, 6, 10), response.getUpdateDate());
    assertTrue(response.getIsVisible());
    assertEquals(5, response.getProductTypeId());
    assertEquals("Categoría A", response.getProductType());
  }
}
