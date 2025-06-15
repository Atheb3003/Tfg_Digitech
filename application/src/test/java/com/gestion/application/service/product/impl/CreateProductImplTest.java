package com.gestion.application.service.product.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ProductRequest;
import com.gestion.application.dto.ProductResponse;
import com.gestion.application.exception.*;
import com.gestion.application.mapper.ProductMapper;
import com.gestion.application.model.Product;
import com.gestion.application.model.ProductType;
import com.gestion.application.repository.ProductRepository;
import com.gestion.application.repository.ProductTypeRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateProductImplTest {

  private ProductRepository productRepo;
  private ProductTypeRepository typeRepo;
  private ProductMapper mapper;
  private CreateProductImpl service;

  @BeforeEach
  void setup() {
    productRepo = mock(ProductRepository.class);
    typeRepo = mock(ProductTypeRepository.class);
    mapper = mock(ProductMapper.class);
    service = new CreateProductImpl(productRepo, typeRepo, mapper);
  }

  @Test
  void testCreateProduct_Success() {
    ProductRequest request = new ProductRequest();
    request.setName("Camiseta");
    request.setPrice(29.99);
    request.setIdProductType(1);

    ProductType type = new ProductType();
    type.setIdProductType(1);
    type.setTypeProduct("Ropa");

    Product productEntity = new Product();
    productEntity.setName("Camiseta");
    productEntity.setPrice(29.99);
    productEntity.setProductType(type);

    Product savedProduct = new Product();
    savedProduct.setIdProduct(10);
    savedProduct.setName("Camiseta");
    savedProduct.setPrice(29.99);
    savedProduct.setCreationDate(LocalDate.now());
    savedProduct.setProductType(type);
    savedProduct.setIsVisible(true);

    ProductResponse response = new ProductResponse();
    response.setIdProduct(10);
    response.setName("Camiseta");

    when(productRepo.existsByName("Camiseta")).thenReturn(false);
    when(typeRepo.findById(1)).thenReturn(Optional.of(type));
    when(mapper.toEntity(request)).thenReturn(productEntity);
    when(productRepo.save(productEntity)).thenReturn(savedProduct);
    when(mapper.toResponse(savedProduct)).thenReturn(response);

    ProductResponse result = service.createPRoduct(request);

    assertNotNull(result);
    assertEquals("Camiseta", result.getName());
    verify(productRepo).save(productEntity);
  }

  @Test
  void testCreateProduct_InvalidData() {
    ProductRequest request = new ProductRequest(); // vacío

    assertThrows(ProductInvalidDataException.class, () -> service.createPRoduct(request));
  }

  @Test
  void testCreateProduct_DuplicateName() {
    ProductRequest request = new ProductRequest();
    request.setName("Camiseta");
    request.setPrice(29.99);
    request.setIdProductType(1);

    when(productRepo.existsByName("Camiseta")).thenReturn(true);

    assertThrows(ProductAlreadyExistsException.class, () -> service.createPRoduct(request));
  }

  @Test
  void testCreateProduct_TypeNotFound() {
    ProductRequest request = new ProductRequest();
    request.setName("Camiseta");
    request.setPrice(29.99);
    request.setIdProductType(999);

    when(productRepo.existsByName("Camiseta")).thenReturn(false);
    when(typeRepo.findById(999)).thenReturn(Optional.empty());

    assertThrows(ProductTypeNotFoundException.class, () -> service.createPRoduct(request));
  }

  @Test
  void testCreateProduct_RepositoryFails() {
    ProductRequest request = new ProductRequest();
    request.setName("Camiseta");
    request.setPrice(29.99);
    request.setIdProductType(1);

    ProductType type = new ProductType();
    type.setIdProductType(1);
    type.setTypeProduct("Ropa");

    Product productEntity = new Product();
    productEntity.setName("Camiseta");
    productEntity.setPrice(29.99);
    productEntity.setProductType(type);

    when(productRepo.existsByName("Camiseta")).thenReturn(false);
    when(typeRepo.findById(1)).thenReturn(Optional.of(type));
    when(mapper.toEntity(request)).thenReturn(productEntity);
    when(productRepo.save(productEntity)).thenThrow(new RuntimeException("DB error"));

    assertThrows(ProductCreationException.class, () -> service.createPRoduct(request));
  }
}
