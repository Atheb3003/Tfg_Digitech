package com.gestion.application.service.product.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ProductResponse;
import com.gestion.application.exception.ProductNotFoundException;
import com.gestion.application.exception.ProductSearchException;
import com.gestion.application.mapper.ProductMapper;
import com.gestion.application.model.Product;
import com.gestion.application.repository.ProductRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetProductByIdImplTest {

  private ProductRepository productRepo;
  private ProductMapper productMapper;
  private GetProductByIdImpl service;

  @BeforeEach
  void setup() {
    productRepo = mock(ProductRepository.class);
    productMapper = mock(ProductMapper.class);
    service = new GetProductByIdImpl(productRepo, productMapper);
  }

  @Test
  void testGetById_Success() {
    Integer productId = 10;
    Product product = new Product();
    product.setIdProduct(productId);
    product.setName("Producto X");

    ProductResponse response = new ProductResponse();
    response.setIdProduct(productId);
    response.setName("Producto X");

    when(productRepo.findById(productId)).thenReturn(Optional.of(product));
    when(productMapper.toResponse(product)).thenReturn(response);

    ProductResponse result = service.getById(productId);

    assertNotNull(result);
    assertEquals("Producto X", result.getName());
  }

  @Test
  void testGetById_ProductNotFound() {
    Integer productId = 999;

    when(productRepo.findById(productId)).thenReturn(Optional.empty());

    assertThrows(ProductNotFoundException.class, () -> service.getById(productId));
  }

  @Test
  void testGetById_UnexpectedError() {
    Integer productId = 1;

    when(productRepo.findById(productId)).thenThrow(new RuntimeException("DB crash"));

    assertThrows(ProductSearchException.class, () -> service.getById(productId));
  }
}
