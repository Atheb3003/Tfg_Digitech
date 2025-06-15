package com.gestion.application.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.gestion.application.dto.RevisionTypeRequest;
import com.gestion.application.dto.RevisionTypeResponse;
import com.gestion.application.model.RevisionType;
import org.junit.jupiter.api.Test;

public class RevisionTypeMapperTest {

  private final RevisionTypeMapper mapper = new RevisionTypeMapper();

  @Test
  void testToEntity() {
    RevisionTypeRequest request = new RevisionTypeRequest();
    request.setTypeName("Revisión Inicial");

    RevisionType entity = mapper.toEntity(request);

    assertEquals("Revisión Inicial", entity.getTypeName());
  }

  @Test
  void testToResponse() {
    RevisionType entity = new RevisionType();
    entity.setIdRevisionType(1);
    entity.setTypeName("Revisión Final");

    RevisionTypeResponse response = mapper.toResponse(entity);

    assertEquals(1, response.getIdRevisionType());
    assertEquals("Revisión Final", response.getTypeName());
  }
}
