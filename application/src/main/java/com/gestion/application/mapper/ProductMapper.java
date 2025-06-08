package com.gestion.application.mapper;

import com.gestion.application.dto.ProductRequest;
import com.gestion.application.dto.ProductResponse;
import com.gestion.application.model.Product;
import com.gestion.application.model.ProductType;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

  public Product toEntity(ProductRequest dto) {
    Product p = new Product();
    p.setName(dto.getName());
    p.setDescription(dto.getDescription());
    p.setPrice(dto.getPrice());

    if (dto.getIdProductType() != null) {
      ProductType pt = new ProductType();
      pt.setIdProductType(dto.getIdProductType());
      p.setProductType(pt);
    }
    return p;
  }

  public ProductResponse toResponse(Product e) {
    ProductResponse r = new ProductResponse();
    r.setIdProduct(e.getIdProduct());
    r.setName(e.getName());
    r.setDescription(e.getDescription());
    r.setPrice(e.getPrice());
    r.setCreationDate(e.getCreationDate());
    r.setUpdateDate(e.getUpdateDate());
    r.setIsVisible(e.getIsVisible());

    if (e.getProductType() != null) {
      r.setProductTypeId(e.getProductType().getIdProductType());
      r.setProductType(e.getProductType().getTypeProduct());
    }
    return r;
  }
}
