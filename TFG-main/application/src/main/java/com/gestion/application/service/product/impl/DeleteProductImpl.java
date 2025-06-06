package com.gestion.application.service.product.impl;

import com.gestion.application.exception.ProductConflictException;
import com.gestion.application.exception.ProductNotFoundException;
import com.gestion.application.repository.ProductRepository;
import com.gestion.application.repository.TransactionDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteProductImpl {

  private final ProductRepository productRepo;
  private final TransactionDetailRepository txDetailRepo;

  public void deleteProduct(Integer id) {
    // 404 si no existe
    if (!productRepo.existsById(id)) {
      throw new ProductNotFoundException(id);
    }
    // 409 si hay transacciones que lo usan
    if (txDetailRepo.existsByProduct_IdProduct(id)) {
      throw new ProductConflictException(id);
    }
    // todo OK: borrar
    productRepo.deleteById(id);
  }
}
