package com.gestion.application.service.product.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ProductResponse;
import com.gestion.application.mapper.ProductMapper;
import com.gestion.application.model.Product;
import com.gestion.application.repository.ProductRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetAllProductsImplTest {

  private ProductRepository productRepo;
  private ProductMapper productMapper;
  private GetAllProductsImpl getAllProductsService;

  @BeforeEach
  void setup() {
    productRepo = mock(ProductRepository.class);
    productMapper = mock(ProductMapper.class);
    getAllProductsService = new GetAllProductsImpl(productRepo, productMapper);
  }

  @Test
  void testGetAllProducts_ReturnsMappedList() {
    Product p1 = new Product();
    p1.setIdProduct(1);
    p1.setName("Producto A");

    Product p2 = new Product();
    p2.setIdProduct(2);
    p2.setName("Producto B");

    ProductResponse r1 = new ProductResponse();
    r1.setIdProduct(1);
    r1.setName("Producto A");

    ProductResponse r2 = new ProductResponse();
    r2.setIdProduct(2);
    r2.setName("Producto B");

    when(productRepo.findAll()).thenReturn(Arrays.asList(p1, p2));
    when(productMapper.toResponse(p1)).thenReturn(r1);
    when(productMapper.toResponse(p2)).thenReturn(r2);

    List<ProductResponse> result = getAllProductsService.getAllProducts();

    assertEquals(2, result.size());
    assertEquals("Producto A", result.get(0).getName());
    assertEquals("Producto B", result.get(1).getName());
  }

  @Test
  void testGetAllProducts_EmptyList() {
    when(productRepo.findAll()).thenReturn(Collections.emptyList());

    List<ProductResponse> result = getAllProductsService.getAllProducts();

    assertNotNull(result);
    assertTrue(result.isEmpty());
  }
}
