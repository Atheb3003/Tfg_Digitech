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

/** Lógica para actualizar un producto existente */
@Service
@RequiredArgsConstructor
public class UpdateProductImpl {

  private final ProductRepository productRepo;
  private final ProductTypeRepository typeRepo;
  private final ProductMapper mapper;

  public ProductResponse update(Integer id, ProductRequest req) {
    // validar nombre
    if (req.getName() == null || req.getName().trim().isEmpty()) {
      throw new ProductInvalidDataException("El campo 'name' no puede estar vacío");
    }

    // validar precio
    if (req.getPrice() == null || req.getPrice() < 0) {
      throw new ProductInvalidDataException("El campo 'price' debe ser un número positivo");
    }

    // validar tipo de producto
    if (req.getIdProductType() == null) {
      throw new ProductInvalidDataException("El campo 'id_productType' es obligatorio");
    }

    // comprobar que existe el producto
    if (!productRepo.existsById(id)) {
      throw new ProductNotFoundException(id);
    }

    // comprobar tipo de producto
    ProductType type =
        typeRepo
            .findById(req.getIdProductType())
            .orElseThrow(() -> new ProductTypeNotFoundException(req.getIdProductType()));

    // cargar entidad y aplicar cambios
    Product p = productRepo.findById(id).get();
    p.setName(req.getName());
    p.setDescription(req.getDescription());
    p.setPrice(req.getPrice());
    p.setProductType(type);
    p.setUpdateDate(LocalDate.now());

    // guardar y devolver
    try {
      Product saved = productRepo.save(p);
      return mapper.toResponse(saved);
    } catch (Exception ex) {
      throw new ProductUpdateException();
    }
  }
}
