package com.gestion.application.service.producttype.impl;

import com.gestion.application.exception.ProductTypeNotFoundException;
import com.gestion.application.repository.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Lógica para eliminar un ProductType por su ID. */
@Service
@RequiredArgsConstructor
public class DeleteProductTypeImpl {

  private final ProductTypeRepository repository;

  public void deleteProductType(Integer id) {
    // 404 si no existe
    if (!repository.existsById(id)) {
      throw new ProductTypeNotFoundException(id);
    }
    repository.deleteById(id);
  }
}
