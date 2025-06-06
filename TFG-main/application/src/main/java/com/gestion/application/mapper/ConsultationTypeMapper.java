package com.gestion.application.mapper;

import com.gestion.application.dto.ConsultationTypeRequest;
import com.gestion.application.dto.ConsultationTypeResponse;
import com.gestion.application.model.ConsultationType;
import org.springframework.stereotype.Component;

@Component
public class ConsultationTypeMapper {

  public ConsultationType toEntity(ConsultationTypeRequest dto) {
    ConsultationType ct = new ConsultationType();
    ct.setTypeName(dto.getTypeName());
    return ct;
  }

  public ConsultationTypeResponse toResponse(ConsultationType entity) {
    ConsultationTypeResponse dto = new ConsultationTypeResponse();
    dto.setIdType(entity.getIdType());
    dto.setTypeName(entity.getTypeName());
    return dto;
  }
}
