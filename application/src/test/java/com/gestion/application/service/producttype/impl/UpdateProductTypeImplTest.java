package com.gestion.application.service.producttype.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ProductTypeRequest;
import com.gestion.application.dto.ProductTypeResponse;
import com.gestion.application.exception.ProductTypeNotFoundException;
import com.gestion.application.mapper.ProductTypeMapper;
import com.gestion.application.model.ProductType;
import com.gestion.application.repository.ProductTypeRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UpdateProductTypeImplTest {

  private ProductTypeRepository repository;
  private ProductTypeMapper mapper;
  private UpdateProductTypeImpl service;

  @BeforeEach
  void setup() {
    repository = mock(ProductTypeRepository.class);
    mapper = mock(ProductTypeMapper.class);
    service = new UpdateProductTypeImpl(repository, mapper);
  }

  @Test
  void testUpdate_Success() {
    Integer id = 1;

    ProductTypeRequest request = new ProductTypeRequest();
    request.setTypeProduct("Actualizado");

    ProductType entity = new ProductType();
    entity.setIdProductType(id);
    entity.setTypeProduct("Antiguo");

    ProductType saved = new ProductType();
    saved.setIdProductType(id);
    saved.setTypeProduct("Actualizado");

    ProductTypeResponse response = new ProductTypeResponse();
    response.setIdProductType(id);
    response.setTypeProduct("Actualizado");

    when(repository.existsById(id)).thenReturn(true);
    when(repository.findById(id)).thenReturn(Optional.of(entity));
    when(repository.save(entity)).thenReturn(saved);
    when(mapper.toResponse(saved)).thenReturn(response);

    ProductTypeResponse result = service.update(id, request);

    assertNotNull(result);
    assertEquals("Actualizado", result.getTypeProduct());
    verify(repository).save(entity);
  }

  @Test
  void testUpdate_NotFound() {
    Integer id = 99;

    ProductTypeRequest request = new ProductTypeRequest();
    request.setTypeProduct("Algo");

    when(repository.existsById(id)).thenReturn(false);

    assertThrows(ProductTypeNotFoundException.class, () -> service.update(id, request));
    verify(repository, never()).findById(any());
    verify(repository, never()).save(any());
  }

  @Test
  void testUpdate_EmptyName() {
    Integer id = 1;

    ProductTypeRequest request = new ProductTypeRequest();
    request.setTypeProduct("   "); // vacío

    when(repository.existsById(id)).thenReturn(true);

    assertThrows(IllegalArgumentException.class, () -> service.update(id, request));
    verify(repository, never()).save(any());
  }

  @Test
  void testUpdate_NullName() {
    Integer id = 1;

    ProductTypeRequest request = new ProductTypeRequest();
    request.setTypeProduct(null);

    when(repository.existsById(id)).thenReturn(true);

    assertThrows(IllegalArgumentException.class, () -> service.update(id, request));
    verify(repository, never()).save(any());
  }
}
