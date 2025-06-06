package com.gestion.application.service.consultationtype.impl;

import com.gestion.application.dto.ConsultationTypeRequest;
import com.gestion.application.dto.ConsultationTypeResponse;
import com.gestion.application.exception.ConsultationTypeInvalidDataException;
import com.gestion.application.mapper.ConsultationTypeMapper;
import com.gestion.application.model.ConsultationType;
import com.gestion.application.repository.ConsultationTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateConsultationTypeImpl {

  private final ConsultationTypeRepository repo;
  private final ConsultationTypeMapper mapper;

  public ConsultationTypeResponse create(ConsultationTypeRequest req) {
    if (req.getTypeName() == null || req.getTypeName().isBlank()) {
      throw new ConsultationTypeInvalidDataException();
    }
    ConsultationType saved = repo.save(mapper.toEntity(req));
    return mapper.toResponse(saved);
  }
}
