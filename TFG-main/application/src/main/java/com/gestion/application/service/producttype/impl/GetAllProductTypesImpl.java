package com.gestion.application.service.producttype.impl;

import com.gestion.application.dto.ProductTypeResponse;
import com.gestion.application.mapper.ProductTypeMapper;
import com.gestion.application.repository.ProductTypeRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllProductTypesImpl {

  private final ProductTypeRepository repository;
  private final ProductTypeMapper mapper;

  public List<ProductTypeResponse> getAll() {
    return repository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
  }
}
