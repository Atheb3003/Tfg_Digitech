package com.gestion.application.service.consultationtype.impl;

import com.gestion.application.dto.ConsultationTypeRequest;
import com.gestion.application.dto.ConsultationTypeResponse;
import com.gestion.application.exception.ConsultationTypeInvalidDataException;
import com.gestion.application.exception.ConsultationTypeNotFoundException;
import com.gestion.application.mapper.ConsultationTypeMapper;
import com.gestion.application.model.ConsultationType;
import com.gestion.application.repository.ConsultationTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateConsultationTypeImpl {

  private final ConsultationTypeRepository repo;
  private final ConsultationTypeMapper mapper;

  @Transactional
  public ConsultationTypeResponse update(Integer id, ConsultationTypeRequest req) {
    ConsultationType existing =
        repo.findById(id).orElseThrow(() -> new ConsultationTypeNotFoundException(id));

    if (req.getTypeName() == null || req.getTypeName().isBlank()) {
      throw new ConsultationTypeInvalidDataException();
    }
    existing.setTypeName(req.getTypeName());
    ConsultationType saved = repo.save(existing);
    return mapper.toResponse(saved);
  }
}
