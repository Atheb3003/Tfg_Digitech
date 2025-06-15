package com.gestion.application.service.product.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.exception.ProductConflictException;
import com.gestion.application.exception.ProductNotFoundException;
import com.gestion.application.repository.ProductRepository;
import com.gestion.application.repository.TransactionDetailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeleteProductImplTest {

  private ProductRepository productRepo;
  private TransactionDetailRepository txDetailRepo;
  private DeleteProductImpl deleteService;

  @BeforeEach
  void setup() {
    productRepo = mock(ProductRepository.class);
    txDetailRepo = mock(TransactionDetailRepository.class);
    deleteService = new DeleteProductImpl(productRepo, txDetailRepo);
  }

  @Test
  void testDeleteProduct_Success() {
    Integer productId = 1;

    when(productRepo.existsById(productId)).thenReturn(true);
    when(txDetailRepo.existsByProduct_IdProduct(productId)).thenReturn(false);

    deleteService.deleteProduct(productId);

    verify(productRepo).deleteById(productId);
  }

  @Test
  void testDeleteProduct_NotFound() {
    Integer productId = 999;

    when(productRepo.existsById(productId)).thenReturn(false);

    assertThrows(ProductNotFoundException.class, () -> deleteService.deleteProduct(productId));
    verify(productRepo, never()).deleteById(any());
  }

  @Test
  void testDeleteProduct_ConflictDueToTransactions() {
    Integer productId = 5;

    when(productRepo.existsById(productId)).thenReturn(true);
    when(txDetailRepo.existsByProduct_IdProduct(productId)).thenReturn(true);

    assertThrows(ProductConflictException.class, () -> deleteService.deleteProduct(productId));
    verify(productRepo, never()).deleteById(any());
  }
}
