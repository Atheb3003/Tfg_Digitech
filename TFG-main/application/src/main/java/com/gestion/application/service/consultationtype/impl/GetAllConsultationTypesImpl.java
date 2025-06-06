package com.gestion.application.service.consultationtype.impl;

import com.gestion.application.dto.ConsultationTypeResponse;
import com.gestion.application.mapper.ConsultationTypeMapper;
import com.gestion.application.repository.ConsultationTypeRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllConsultationTypesImpl {

  private final ConsultationTypeRepository repo;
  private final ConsultationTypeMapper mapper;

  public List<ConsultationTypeResponse> getAll() {
    return repo.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
  }
}
