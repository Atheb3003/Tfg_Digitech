package com.gestion.application.service.producttype.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ProductTypeRequest;
import com.gestion.application.dto.ProductTypeResponse;
import com.gestion.application.mapper.ProductTypeMapper;
import com.gestion.application.model.ProductType;
import com.gestion.application.repository.ProductTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateProductTypeImplTest {

  private ProductTypeRepository repository;
  private ProductTypeMapper mapper;
  private CreateProductTypeImpl service;

  @BeforeEach
  void setup() {
    repository = mock(ProductTypeRepository.class);
    mapper = mock(ProductTypeMapper.class);
    service = new CreateProductTypeImpl(repository, mapper);
  }

  @Test
  void testCreateProductType_Success() {
    ProductTypeRequest request = new ProductTypeRequest();
    request.setTypeProduct("Electrónica");

    ProductType entity = new ProductType();
    entity.setTypeProduct("Electrónica");

    ProductType saved = new ProductType();
    saved.setIdProductType(1);
    saved.setTypeProduct("Electrónica");

    ProductTypeResponse response = new ProductTypeResponse();
    response.setIdProductType(1);
    response.setTypeProduct("Electrónica");

    when(mapper.toEntity(request)).thenReturn(entity);
    when(repository.save(entity)).thenReturn(saved);
    when(mapper.toResponse(saved)).thenReturn(response);

    ProductTypeResponse result = service.create(request);

    assertNotNull(result);
    assertEquals("Electrónica", result.getTypeProduct());
    assertEquals(1, result.getIdProductType());
    verify(repository).save(entity);
  }

  @Test
  void testCreateProductType_EmptyTypeProduct_ThrowsException() {
    ProductTypeRequest request = new ProductTypeRequest();
    request.setTypeProduct("   "); // vacío después de trim

    IllegalArgumentException ex =
        assertThrows(IllegalArgumentException.class, () -> service.create(request));

    assertEquals("El tipo de producto no puede estar vacío.", ex.getMessage());
    verify(repository, never()).save(any());
  }

  @Test
  void testCreateProductType_NullTypeProduct_ThrowsException() {
    ProductTypeRequest request = new ProductTypeRequest();
    request.setTypeProduct(null);

    IllegalArgumentException ex =
        assertThrows(IllegalArgumentException.class, () -> service.create(request));

    assertEquals("El tipo de producto no puede estar vacío.", ex.getMessage());
    verify(repository, never()).save(any());
  }
}
