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

class GetVisibleProductsImplTest {

  private ProductRepository productRepo;
  private ProductMapper productMapper;
  private GetVisibleProductsImpl getVisibleProductsService;

  @BeforeEach
  void setup() {
    productRepo = mock(ProductRepository.class);
    productMapper = mock(ProductMapper.class);
    getVisibleProductsService = new GetVisibleProductsImpl(productRepo, productMapper);
  }

  @Test
  void testGetVisibleProducts_ReturnsMappedList() {
    Product p1 = new Product();
    p1.setIdProduct(1);
    p1.setName("Visible A");

    Product p2 = new Product();
    p2.setIdProduct(2);
    p2.setName("Visible B");

    ProductResponse r1 = new ProductResponse();
    r1.setIdProduct(1);
    r1.setName("Visible A");

    ProductResponse r2 = new ProductResponse();
    r2.setIdProduct(2);
    r2.setName("Visible B");

    when(productRepo.findByIsVisibleTrue()).thenReturn(Arrays.asList(p1, p2));
    when(productMapper.toResponse(p1)).thenReturn(r1);
    when(productMapper.toResponse(p2)).thenReturn(r2);

    List<ProductResponse> result = getVisibleProductsService.getVisibleProducts();

    assertEquals(2, result.size());
    assertEquals("Visible A", result.get(0).getName());
    assertEquals("Visible B", result.get(1).getName());
  }

  @Test
  void testGetVisibleProducts_EmptyList() {
    when(productRepo.findByIsVisibleTrue()).thenReturn(Collections.emptyList());

    List<ProductResponse> result = getVisibleProductsService.getVisibleProducts();

    assertNotNull(result);
    assertTrue(result.isEmpty());
  }
}
