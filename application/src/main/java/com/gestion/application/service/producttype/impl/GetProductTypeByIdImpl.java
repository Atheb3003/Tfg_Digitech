package com.gestion.application.service.producttype.impl;

import com.gestion.application.dto.ProductTypeResponse;
import com.gestion.application.exception.ProductTypeNotFoundException;
import com.gestion.application.mapper.ProductTypeMapper;
import com.gestion.application.model.ProductType;
import com.gestion.application.repository.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Lógica para obtener un ProductType por su ID. */
@Service
@RequiredArgsConstructor
public class GetProductTypeByIdImpl {

  private final ProductTypeRepository repository;
  private final ProductTypeMapper mapper;

  /** Busca y convierte a DTO. Si no existe, lanza ProductTypeNotFoundException. */
  public ProductTypeResponse getById(Integer id) {
    // Primero comprobamos que exista el id
    if (!repository.existsById(id)) {
      // Si no existe mandamos la excepcion
      throw new ProductTypeNotFoundException(id);
    }
    // Sabemos que existe, así que recuperamos y convertimos
    ProductType entity = repository.findById(id).get();
    return mapper.toResponse(entity);
  }
}
