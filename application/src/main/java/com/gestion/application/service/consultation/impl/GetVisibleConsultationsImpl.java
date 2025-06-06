package com.gestion.application.service.consultation.impl;

import com.gestion.application.dto.ConsultationResponse;
import com.gestion.application.mapper.ConsultationMapper;
import com.gestion.application.repository.ConsultationRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetVisibleConsultationsImpl {

  private final ConsultationRepository repo;
  private final ConsultationMapper mapper;

  public List<ConsultationResponse> getVisible() {
    return repo.findAllByIsVisibleTrue().stream()
        .map(mapper::toResponse)
        .collect(Collectors.toList());
  }
}
