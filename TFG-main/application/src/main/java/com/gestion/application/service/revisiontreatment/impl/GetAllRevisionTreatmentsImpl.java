package com.gestion.application.service.revisiontreatment.impl;

import com.gestion.application.dto.RevisionTreatmentResponse;
import com.gestion.application.mapper.RevisionTreatmentMapper;
import com.gestion.application.repository.RevisionTreatmentRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllRevisionTreatmentsImpl {
  private final RevisionTreatmentRepository repo;
  private final RevisionTreatmentMapper mapper;

  public List<RevisionTreatmentResponse> getAll() {
    return repo.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
  }
}
