package com.gestion.application.service.product.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ProductResponse;
import com.gestion.application.mapper.ProductMapper;
import com.gestion.application.model.Product;
import com.gestion.application.repository.ProductRepository;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.*;

class GetVisibleProductsPageImplTest {

  private ProductRepository productRepo;
  private ProductMapper productMapper;
  private GetVisibleProductsPageImpl service;

  @BeforeEach
  void setup() {
    productRepo = mock(ProductRepository.class);
    productMapper = mock(ProductMapper.class);
    service = new GetVisibleProductsPageImpl(productRepo, productMapper);
  }

  @Test
  void testGetVisibleProducts_ReturnsMappedPage() {
    Pageable pageable = PageRequest.of(0, 2);
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

    Page<Product> productPage = new PageImpl<>(Arrays.asList(p1, p2), pageable, 2);
    when(productRepo.findByIsVisibleTrue(pageable)).thenReturn(productPage);
    when(productMapper.toResponse(p1)).thenReturn(r1);
    when(productMapper.toResponse(p2)).thenReturn(r2);

    Page<ProductResponse> result = service.getVisibleProducts(pageable);

    assertEquals(2, result.getContent().size());
    assertEquals("Producto A", result.getContent().get(0).getName());
    assertEquals("Producto B", result.getContent().get(1).getName());
  }

  @Test
  void testGetVisibleProducts_EmptyPage() {
    Pageable pageable = PageRequest.of(0, 2);
    Page<Product> emptyPage = new PageImpl<>(Collections.emptyList(), pageable, 0);

    when(productRepo.findByIsVisibleTrue(pageable)).thenReturn(emptyPage);

    Page<ProductResponse> result = service.getVisibleProducts(pageable);

    assertNotNull(result);
    assertTrue(result.getContent().isEmpty());
    assertEquals(0, result.getTotalElements());
  }
}
