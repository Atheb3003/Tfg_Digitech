package com.gestion.application.mapper;

import com.gestion.application.dto.RevisionTypeRequest;
import com.gestion.application.dto.RevisionTypeResponse;
import com.gestion.application.model.RevisionType;
import org.springframework.stereotype.Component;

@Component
public class RevisionTypeMapper {
  public RevisionType toEntity(RevisionTypeRequest dto) {
    RevisionType rt = new RevisionType();
    rt.setTypeName(dto.getTypeName());
    return rt;
  }

  public RevisionTypeResponse toResponse(RevisionType e) {
    RevisionTypeResponse dto = new RevisionTypeResponse();
    dto.setIdRevisionType(e.getIdRevisionType());
    dto.setTypeName(e.getTypeName());
    return dto;
  }
}
