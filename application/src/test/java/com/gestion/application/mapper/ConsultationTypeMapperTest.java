package com.gestion.application.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.gestion.application.dto.ConsultationTypeRequest;
import com.gestion.application.dto.ConsultationTypeResponse;
import com.gestion.application.model.ConsultationType;
import org.junit.jupiter.api.Test;

public class ConsultationTypeMapperTest {

  private final ConsultationTypeMapper mapper = new ConsultationTypeMapper();

  @Test
  void testToEntity() {
    ConsultationTypeRequest request = new ConsultationTypeRequest();
    request.setTypeName("Consulta Especial");

    ConsultationType entity = mapper.toEntity(request);

    assertEquals("Consulta Especial", entity.getTypeName());
  }

  @Test
  void testToResponse() {
    ConsultationType entity = new ConsultationType();
    entity.setIdType(5);
    entity.setTypeName("Consulta General");

    ConsultationTypeResponse response = mapper.toResponse(entity);

    assertEquals(5, response.getIdType());
    assertEquals("Consulta General", response.getTypeName());
  }
}
