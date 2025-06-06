package com.gestion.application.service.producttype.impl;

import com.gestion.application.dto.ProductTypeRequest;
import com.gestion.application.dto.ProductTypeResponse;
import com.gestion.application.mapper.ProductTypeMapper;
import com.gestion.application.model.ProductType;
import com.gestion.application.repository.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProductTypeImpl {

  private final ProductTypeRepository repository;
  private final ProductTypeMapper mapper;

  public ProductTypeResponse create(ProductTypeRequest request) {
    if (request.getTypeProduct() == null || request.getTypeProduct().trim().isEmpty()) {
      throw new IllegalArgumentException("El tipo de producto no puede estar vacío.");
    }

    ProductType entity = mapper.toEntity(request);
    ProductType saved = repository.save(entity);
    return mapper.toResponse(saved);
  }
}
