package com.gestion.application.service.product.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ProductResponse;
import com.gestion.application.exception.ProductNotFoundException;
import com.gestion.application.exception.ProductUpdateException;
import com.gestion.application.mapper.ProductMapper;
import com.gestion.application.model.Product;
import com.gestion.application.repository.ProductRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ToggleProductVisibilityImplTest {

  private ProductRepository productRepo;
  private ProductMapper productMapper;
  private ToggleProductVisibilityImpl service;

  @BeforeEach
  void setup() {
    productRepo = mock(ProductRepository.class);
    productMapper = mock(ProductMapper.class);
    service = new ToggleProductVisibilityImpl(productRepo, productMapper);
  }

  @Test
  void testToggleVisibility_Success() {
    Integer id = 1;

    Product existing = new Product();
    existing.setIdProduct(id);
    existing.setIsVisible(true);

    Product updated = new Product();
    updated.setIdProduct(id);
    updated.setIsVisible(false);
    updated.setUpdateDate(LocalDate.now());

    ProductResponse response = new ProductResponse();
    response.setIdProduct(id);
    response.setIsVisible(false);

    when(productRepo.findById(id)).thenReturn(Optional.of(existing));
    when(productRepo.save(any(Product.class))).thenReturn(updated);
    when(productMapper.toResponse(updated)).thenReturn(response);

    ProductResponse result = service.toggleVisibility(id);

    assertNotNull(result);
    assertFalse(result.getIsVisible());
    verify(productRepo).save(existing);
  }

  @Test
  void testToggleVisibility_NotFound() {
    Integer id = 99;

    when(productRepo.findById(id)).thenReturn(Optional.empty());

    assertThrows(ProductNotFoundException.class, () -> service.toggleVisibility(id));
    verify(productRepo, never()).save(any());
  }

  @Test
  void testToggleVisibility_SaveFails() {
    Integer id = 5;

    Product existing = new Product();
    existing.setIdProduct(id);
    existing.setIsVisible(true);

    when(productRepo.findById(id)).thenReturn(Optional.of(existing));
    when(productRepo.save(any(Product.class))).thenThrow(new RuntimeException("DB error"));

    assertThrows(ProductUpdateException.class, () -> service.toggleVisibility(id));
  }
}
