package com.gestion.application.service.product.impl;

import com.gestion.application.dto.ProductResponse;
import com.gestion.application.exception.ProductNotFoundException;
import com.gestion.application.exception.ProductUpdateException;
import com.gestion.application.mapper.ProductMapper;
import com.gestion.application.model.Product;
import com.gestion.application.repository.ProductRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Lógica para invertir la visibilidad de un producto */
@Service
@RequiredArgsConstructor
public class ToggleProductVisibilityImpl {

  private final ProductRepository repository;
  private final ProductMapper mapper;

  public ProductResponse toggleVisibility(Integer id) {
    // 404 si no existe
    Product p = repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

    // invertimos la visibilidad
    p.setIsVisible(!p.getIsVisible());
    p.setUpdateDate(LocalDate.now());

    // guardamos y devolvemos
    try {
      Product saved = repository.save(p);
      return mapper.toResponse(saved);
    } catch (Exception ex) {
      throw new ProductUpdateException();
    }
  }
}
