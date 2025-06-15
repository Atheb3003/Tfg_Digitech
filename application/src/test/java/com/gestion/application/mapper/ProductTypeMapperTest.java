package com.gestion.application.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.gestion.application.dto.ProductTypeRequest;
import com.gestion.application.dto.ProductTypeResponse;
import com.gestion.application.model.ProductType;
import org.junit.jupiter.api.Test;

public class ProductTypeMapperTest {

  private final ProductTypeMapper mapper = new ProductTypeMapper();

  @Test
  void testToEntity() {
    ProductTypeRequest request = new ProductTypeRequest();
    request.setTypeProduct("Medicamento");

    ProductType entity = mapper.toEntity(request);

    assertEquals("Medicamento", entity.getTypeProduct());
    // El ID no se establece aquí, por eso debe ser null
    assertNull(entity.getIdProductType());
  }

  @Test
  void testToResponse() {
    ProductType entity = new ProductType();
    entity.setIdProductType(5);
    entity.setTypeProduct("Cosmético");

    ProductTypeResponse response = mapper.toResponse(entity);

    assertEquals(5, response.getIdProductType());
    assertEquals("Cosmético", response.getTypeProduct());
  }
}
