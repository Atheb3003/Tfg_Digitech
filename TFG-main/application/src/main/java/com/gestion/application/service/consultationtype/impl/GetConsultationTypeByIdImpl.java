package com.gestion.application.service.consultationtype.impl;

import com.gestion.application.dto.ConsultationTypeResponse;
import com.gestion.application.exception.ConsultationTypeNotFoundException;
import com.gestion.application.mapper.ConsultationTypeMapper;
import com.gestion.application.model.ConsultationType;
import com.gestion.application.repository.ConsultationTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetConsultationTypeByIdImpl {

  private final ConsultationTypeRepository repo;
  private final ConsultationTypeMapper mapper;

  public ConsultationTypeResponse getById(Integer id) {
    ConsultationType ct =
        repo.findById(id).orElseThrow(() -> new ConsultationTypeNotFoundException(id));
    return mapper.toResponse(ct);
  }
}
