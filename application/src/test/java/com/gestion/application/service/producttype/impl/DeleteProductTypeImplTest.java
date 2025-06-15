package com.gestion.application.service.producttype.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.exception.ProductTypeNotFoundException;
import com.gestion.application.repository.ProductTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeleteProductTypeImplTest {

  private ProductTypeRepository repository;
  private DeleteProductTypeImpl service;

  @BeforeEach
  void setup() {
    repository = mock(ProductTypeRepository.class);
    service = new DeleteProductTypeImpl(repository);
  }

  @Test
  void testDeleteProductType_Success() {
    Integer id = 1;

    when(repository.existsById(id)).thenReturn(true);

    service.deleteProductType(id);

    verify(repository).deleteById(id);
  }

  @Test
  void testDeleteProductType_NotFound() {
    Integer id = 99;

    when(repository.existsById(id)).thenReturn(false);

    assertThrows(ProductTypeNotFoundException.class, () -> service.deleteProductType(id));
    verify(repository, never()).deleteById(any());
  }
}
