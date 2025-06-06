package com.gestion.application.mapper;

import com.gestion.application.dto.ProductTypeRequest;
import com.gestion.application.dto.ProductTypeResponse;
import com.gestion.application.model.ProductType;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeMapper {

  public ProductType toEntity(ProductTypeRequest request) {
    ProductType type = new ProductType();
    // No se asigna el ID aquí
    type.setTypeProduct(request.getTypeProduct());
    return type;
  }

  public ProductTypeResponse toResponse(ProductType entity) {
    ProductTypeResponse response = new ProductTypeResponse();
    response.setIdProductType(entity.getIdProductType());
    response.setTypeProduct(entity.getTypeProduct());
    return response;
  }
}
