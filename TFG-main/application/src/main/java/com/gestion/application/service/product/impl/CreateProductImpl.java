package com.gestion.application.service.product.impl;

import com.gestion.application.dto.ProductRequest;
import com.gestion.application.dto.ProductResponse;
import com.gestion.application.exception.*;
import com.gestion.application.mapper.ProductMapper;
import com.gestion.application.model.Product;
import com.gestion.application.model.ProductType;
import com.gestion.application.repository.ProductRepository;
import com.gestion.application.repository.ProductTypeRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Lógica para crear productos */
@Service
@RequiredArgsConstructor
public class CreateProductImpl {

  private final ProductRepository productRepo;
  private final ProductTypeRepository typeRepo;
  private final ProductMapper mapper;

  public ProductResponse createPRoduct(ProductRequest req) {
    // 1) validar campos
    if (req.getName() == null
        || req.getName().trim().isEmpty()
        || req.getPrice() == null
        || req.getPrice() < 0
        || req.getIdProductType() == null) {
      throw new ProductInvalidDataException();
    }

    // 2) nombre único
    if (productRepo.existsByName(req.getName().trim())) {
      throw new ProductAlreadyExistsException(req.getName().trim());
    }

    // 3) buscar tipo
    ProductType type =
        typeRepo
            .findById(req.getIdProductType())
            .orElseThrow(() -> new ProductTypeNotFoundException(req.getIdProductType()));

    // 4) mapear y completar
    Product p = mapper.toEntity(req);
    p.setProductType(type);
    p.setCreationDate(LocalDate.now());
    p.setUpdateDate(null);
    p.setIsVisible(true);

    // 5) guardar y devolver
    try {
      Product saved = productRepo.save(p);
      return mapper.toResponse(saved);
    } catch (Exception ex) {
      throw new ProductCreationException();
    }
  }
}
