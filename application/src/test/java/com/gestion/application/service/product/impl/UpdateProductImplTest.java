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

class UpdateProductImplTest {

  private ProductRepository productRepo;
  private ProductTypeRepository typeRepo;
  private ProductMapper mapper;
  private UpdateProductImpl service;

  @BeforeEach
  void setup() {
    productRepo = mock(ProductRepository.class);
    typeRepo = mock(ProductTypeRepository.class);
    mapper = mock(ProductMapper.class);
    service = new UpdateProductImpl(productRepo, typeRepo, mapper);
  }

  @Test
  void testUpdate_Success() {
    Integer id = 1;

    ProductRequest req = new ProductRequest();
    req.setName("Producto actualizado");
    req.setPrice(99.99);
    req.setDescription("Desc");
    req.setIdProductType(2);

    Product existing = new Product();
    existing.setIdProduct(id);
    existing.setName("Viejo nombre");

    ProductType type = new ProductType();
    type.setIdProductType(2);
    type.setTypeProduct("Nuevo tipo");

    Product updated = new Product();
    updated.setIdProduct(id);
    updated.setName("Producto actualizado");
    updated.setPrice(99.99);
    updated.setProductType(type);
    updated.setUpdateDate(LocalDate.now());

    ProductResponse response = new ProductResponse();
    response.setIdProduct(id);
    response.setName("Producto actualizado");

    when(productRepo.existsById(id)).thenReturn(true);
    when(typeRepo.findById(2)).thenReturn(Optional.of(type));
    when(productRepo.findById(id)).thenReturn(Optional.of(existing));
    when(productRepo.save(existing)).thenReturn(updated);
    when(mapper.toResponse(updated)).thenReturn(response);

    ProductResponse result = service.update(id, req);

    assertEquals("Producto actualizado", result.getName());
    verify(productRepo).save(existing);
  }

  @Test
  void testUpdate_InvalidName() {
    ProductRequest req = new ProductRequest();
    req.setName("   ");
    req.setPrice(20.0);
    req.setIdProductType(1);

    assertThrows(ProductInvalidDataException.class, () -> service.update(1, req));
  }

  @Test
  void testUpdate_InvalidPrice() {
    ProductRequest req = new ProductRequest();
    req.setName("Test");
    req.setPrice(-10.0);
    req.setIdProductType(1);

    assertThrows(ProductInvalidDataException.class, () -> service.update(1, req));
  }

  @Test
  void testUpdate_MissingTypeId() {
    ProductRequest req = new ProductRequest();
    req.setName("Test");
    req.setPrice(10.0);
    req.setIdProductType(null);

    assertThrows(ProductInvalidDataException.class, () -> service.update(1, req));
  }

  @Test
  void testUpdate_ProductNotFound() {
    ProductRequest req = new ProductRequest();
    req.setName("Test");
    req.setPrice(10.0);
    req.setIdProductType(1);

    when(productRepo.existsById(1)).thenReturn(false);

    assertThrows(ProductNotFoundException.class, () -> service.update(1, req));
  }

  @Test
  void testUpdate_ProductTypeNotFound() {
    ProductRequest req = new ProductRequest();
    req.setName("Test");
    req.setPrice(10.0);
    req.setIdProductType(99);

    when(productRepo.existsById(1)).thenReturn(true);
    when(typeRepo.findById(99)).thenReturn(Optional.empty());

    assertThrows(ProductTypeNotFoundException.class, () -> service.update(1, req));
  }

  @Test
  void testUpdate_SaveFails() {
    ProductRequest req = new ProductRequest();
    req.setName("Test");
    req.setPrice(10.0);
    req.setIdProductType(1);

    ProductType type = new ProductType();
    type.setIdProductType(1);

    Product existing = new Product();
    existing.setIdProduct(1);

    when(productRepo.existsById(1)).thenReturn(true);
    when(typeRepo.findById(1)).thenReturn(Optional.of(type));
    when(productRepo.findById(1)).thenReturn(Optional.of(existing));
    when(productRepo.save(existing)).thenThrow(new RuntimeException("DB fail"));

    assertThrows(ProductUpdateException.class, () -> service.update(1, req));
  }
}
