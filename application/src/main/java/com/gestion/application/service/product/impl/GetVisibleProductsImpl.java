package com.gestion.application.service.product.impl;

import com.gestion.application.dto.ProductResponse;
import com.gestion.application.mapper.ProductMapper;
import com.gestion.application.model.Product;
import com.gestion.application.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Lógica para listar sólo productos visibles */
@Service
@RequiredArgsConstructor
public class GetVisibleProductsImpl {

  private final ProductRepository repository;
  private final ProductMapper mapper;

  public List<ProductResponse> getVisibleProducts() {
    List<Product> all = repository.findByIsVisibleTrue();
    List<ProductResponse> out = new ArrayList<>();
    for (Product p : all) {
      out.add(mapper.toResponse(p));
    }
    return out;
  }
}
