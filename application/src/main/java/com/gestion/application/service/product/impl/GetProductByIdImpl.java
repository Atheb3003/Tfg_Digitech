package com.gestion.application.service.product.impl;

import com.gestion.application.dto.ProductResponse;
import com.gestion.application.exception.ProductNotFoundException;
import com.gestion.application.exception.ProductSearchException;
import com.gestion.application.mapper.ProductMapper;
import com.gestion.application.model.Product;
import com.gestion.application.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetProductByIdImpl {

  private final ProductRepository repository;
  private final ProductMapper mapper;

  public ProductResponse getById(Integer id) {
    try {
      Product p = repository
              .findById(id)
              .orElseThrow(() -> new ProductNotFoundException(id));
      return mapper.toResponse(p);
    } catch (ProductNotFoundException ex) {

      throw ex;
    } catch (Exception ex) {

      throw new ProductSearchException();
    }
  }
}
