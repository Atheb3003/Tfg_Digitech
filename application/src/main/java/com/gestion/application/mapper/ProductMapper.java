package com.gestion.application.mapper;

import com.gestion.application.dto.ProductRequest;
import com.gestion.application.dto.ProductResponse;
import com.gestion.application.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

  /** Pasa del DTO de petición a la entidad */
  public Product toEntity(ProductRequest dto) {
    Product p = new Product();
    p.setName(dto.getName());
    p.setDescription(dto.getDescription());
    p.setPrice(dto.getPrice());
    return p;
  }

  /** Pasa de la entidad al DTO de respuesta */
  public ProductResponse toResponse(Product e) {
    ProductResponse r = new ProductResponse();
    r.setIdProduct(e.getIdProduct());
    r.setName(e.getName());
    r.setDescription(e.getDescription());
    r.setPrice(e.getPrice());
    r.setCreationDate(e.getCreationDate());
    r.setUpdateDate(e.getUpdateDate());
    r.setIdProductType(e.getProductType() != null ? e.getProductType().getIdProductType() : null);
    r.setIsVisible(e.getIsVisible());
    return r;
  }
}
