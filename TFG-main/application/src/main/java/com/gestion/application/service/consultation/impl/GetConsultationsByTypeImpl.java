// src/main/java/com/gestion/application/service/consultation/impl/GetConsultationsByTypeImpl.java
package com.gestion.application.service.consultation.impl;

import com.gestion.application.dto.ConsultationResponse;
import com.gestion.application.exception.ConsultationTypeNotFoundException;
import com.gestion.application.mapper.ConsultationMapper;
import com.gestion.application.repository.ConsultationRepository;
import com.gestion.application.repository.ConsultationTypeRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetConsultationsByTypeImpl {

  private final ConsultationRepository repo;
  private final ConsultationTypeRepository typeRepo;
  private final ConsultationMapper mapper;

  public List<ConsultationResponse> getByType(Integer typeId) {
    typeRepo.findById(typeId).orElseThrow(() -> new ConsultationTypeNotFoundException(typeId));
    return repo.findByType_IdType(typeId).stream()
        .map(mapper::toResponse)
        .collect(Collectors.toList());
  }
}
