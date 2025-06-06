package com.gestion.application.service.producttype.impl;

import com.gestion.application.dto.ProductTypeRequest;
import com.gestion.application.dto.ProductTypeResponse;
import com.gestion.application.exception.ProductTypeNotFoundException;
import com.gestion.application.mapper.ProductTypeMapper;
import com.gestion.application.model.ProductType;
import com.gestion.application.repository.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Lógica para actualizar un ProductType. */
@Service
@RequiredArgsConstructor
public class UpdateProductTypeImpl {

  private final ProductTypeRepository repository;
  private final ProductTypeMapper mapper;

  /**
   * Actualiza el nombre de tipoProducto del ID dado.
   *
   * @throws ProductTypeNotFoundException si no existe ese ID (404)
   * @throws IllegalArgumentException si el nombre viene vacío (400)
   */
  public ProductTypeResponse update(Integer id, ProductTypeRequest request) {
    // 1) Comprobar existencia
    if (!repository.existsById(id)) {
      throw new ProductTypeNotFoundException(id);
    }
    // 2) Validar cuerpo
    if (request.getTypeProduct() == null || request.getTypeProduct().trim().isEmpty()) {
      throw new IllegalArgumentException("El tipo de producto no puede estar vacío.");
    }
    // 3) Recuperar, modificar y guardar
    ProductType entity = repository.findById(id).get();
    entity.setTypeProduct(request.getTypeProduct().trim());
    ProductType saved = repository.save(entity);
    // 4) Convertir a DTO
    return mapper.toResponse(saved);
  }
}
