package com.gestion.application.service.producttype.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ProductTypeResponse;
import com.gestion.application.mapper.ProductTypeMapper;
import com.gestion.application.model.ProductType;
import com.gestion.application.repository.ProductTypeRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetAllProductTypesImplTest {

  private ProductTypeRepository repository;
  private ProductTypeMapper mapper;
  private GetAllProductTypesImpl service;

  @BeforeEach
  void setup() {
    repository = mock(ProductTypeRepository.class);
    mapper = mock(ProductTypeMapper.class);
    service = new GetAllProductTypesImpl(repository, mapper);
  }

  @Test
  void testGetAll_ReturnsMappedList() {
    ProductType type1 = new ProductType();
    type1.setIdProductType(1);
    type1.setTypeProduct("Ropa");

    ProductType type2 = new ProductType();
    type2.setIdProductType(2);
    type2.setTypeProduct("Electrónica");

    ProductTypeResponse r1 = new ProductTypeResponse();
    r1.setIdProductType(1);
    r1.setTypeProduct("Ropa");

    ProductTypeResponse r2 = new ProductTypeResponse();
    r2.setIdProductType(2);
    r2.setTypeProduct("Electrónica");

    when(repository.findAll()).thenReturn(Arrays.asList(type1, type2));
    when(mapper.toResponse(type1)).thenReturn(r1);
    when(mapper.toResponse(type2)).thenReturn(r2);

    List<ProductTypeResponse> result = service.getAll();

    assertEquals(2, result.size());
    assertEquals("Ropa", result.get(0).getTypeProduct());
    assertEquals("Electrónica", result.get(1).getTypeProduct());
  }

  @Test
  void testGetAll_EmptyList() {
    when(repository.findAll()).thenReturn(Collections.emptyList());

    List<ProductTypeResponse> result = service.getAll();

    assertNotNull(result);
    assertTrue(result.isEmpty());
  }
}
