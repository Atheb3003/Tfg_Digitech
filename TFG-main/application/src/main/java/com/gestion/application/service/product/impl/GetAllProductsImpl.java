package com.gestion.application.service.product.impl;

import com.gestion.application.dto.ProductResponse;
import com.gestion.application.mapper.ProductMapper;
import com.gestion.application.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Lógica para listar todos los productos */
@Service
@RequiredArgsConstructor
public class GetAllProductsImpl {

  private final ProductRepository repository;
  private final ProductMapper mapper;

  public List<ProductResponse> getAllProducts() {
    return repository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
  }
}
