package com.gestion.application.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.gestion.application.dto.ProductRequest;
import com.gestion.application.dto.ProductResponse;
import com.gestion.application.model.Product;
import com.gestion.application.model.ProductType;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class ProductMapperTest {

  private final ProductMapper mapper = new ProductMapper();

  @Test
  void testToEntity_WithProductType() {
    ProductRequest request = new ProductRequest();
    request.setName("Producto X");
    request.setDescription("Descripción X");
    request.setPrice(120.50);
    request.setIdProductType(10);

    Product entity = mapper.toEntity(request);

    assertEquals("Producto X", entity.getName());
    assertEquals("Descripción X", entity.getDescription());
    assertEquals(120.50, entity.getPrice());
    assertNotNull(entity.getProductType());
    assertEquals(10, entity.getProductType().getIdProductType());
  }

  @Test
  void testToEntity_WithoutProductType() {
    ProductRequest request = new ProductRequest();
    request.setName("Producto Y");
    request.setDescription("Descripción Y");
    request.setPrice(99.99);
    request.setIdProductType(null);

    Product entity = mapper.toEntity(request);

    assertEquals("Producto Y", entity.getName());
    assertEquals("Descripción Y", entity.getDescription());
    assertEquals(99.99, entity.getPrice());
    assertNull(entity.getProductType());
  }

  @Test
  void testToResponse_WithProductType() {
    Product entity = new Product();
    entity.setIdProduct(1001);
    entity.setName("Producto Z");
    entity.setDescription("Descripción Z");
    entity.setPrice(200.00);
    entity.setCreationDate(LocalDate.of(2025, 6, 1));
    entity.setUpdateDate(LocalDate.of(2025, 6, 10));
    entity.setIsVisible(true);

    ProductType productType = new ProductType();
    productType.setIdProductType(20);
    productType.setTypeProduct("Cosmético");
    entity.setProductType(productType);

    ProductResponse response = mapper.toResponse(entity);

    assertEquals(1001, response.getIdProduct());
    assertEquals("Producto Z", response.getName());
    assertEquals("Descripción Z", response.getDescription());
    assertEquals(200.00, response.getPrice());
    assertEquals(LocalDate.of(2025, 6, 1), response.getCreationDate());
    assertEquals(LocalDate.of(2025, 6, 10), response.getUpdateDate());
    assertTrue(response.getIsVisible());
    assertEquals(20, response.getProductTypeId());
    assertEquals("Cosmético", response.getProductType());
  }

  @Test
  void testToResponse_WithoutProductType() {
    Product entity = new Product();
    entity.setIdProduct(2002);
    entity.setName("Producto W");
    entity.setDescription("Descripción W");
    entity.setPrice(150.00);
    entity.setCreationDate(LocalDate.of(2025, 5, 1));
    entity.setUpdateDate(LocalDate.of(2025, 5, 10));
    entity.setIsVisible(false);
    entity.setProductType(null);

    ProductResponse response = mapper.toResponse(entity);

    assertEquals(2002, response.getIdProduct());
    assertEquals("Producto W", response.getName());
    assertEquals("Descripción W", response.getDescription());
    assertEquals(150.00, response.getPrice());
    assertEquals(LocalDate.of(2025, 5, 1), response.getCreationDate());
    assertEquals(LocalDate.of(2025, 5, 10), response.getUpdateDate());
    assertFalse(response.getIsVisible());
    assertNull(response.getProductTypeId());
    assertNull(response.getProductType());
  }
}
