package com.gestion.application.service.producttype.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ProductTypeResponse;
import com.gestion.application.exception.ProductTypeNotFoundException;
import com.gestion.application.mapper.ProductTypeMapper;
import com.gestion.application.model.ProductType;
import com.gestion.application.repository.ProductTypeRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetProductTypeByIdImplTest {

  private ProductTypeRepository repository;
  private ProductTypeMapper mapper;
  private GetProductTypeByIdImpl service;

  @BeforeEach
  void setup() {
    repository = mock(ProductTypeRepository.class);
    mapper = mock(ProductTypeMapper.class);
    service = new GetProductTypeByIdImpl(repository, mapper);
  }

  @Test
  void testGetById_Success() {
    Integer id = 1;

    ProductType entity = new ProductType();
    entity.setIdProductType(id);
    entity.setTypeProduct("Ropa");

    ProductTypeResponse response = new ProductTypeResponse();
    response.setIdProductType(id);
    response.setTypeProduct("Ropa");

    when(repository.existsById(id)).thenReturn(true);
    when(repository.findById(id)).thenReturn(Optional.of(entity));
    when(mapper.toResponse(entity)).thenReturn(response);

    ProductTypeResponse result = service.getById(id);

    assertNotNull(result);
    assertEquals("Ropa", result.getTypeProduct());
    verify(repository).findById(id);
  }

  @Test
  void testGetById_NotFound() {
    Integer id = 999;

    when(repository.existsById(id)).thenReturn(false);

    assertThrows(ProductTypeNotFoundException.class, () -> service.getById(id));
    verify(repository, never()).findById(id);
  }
}
