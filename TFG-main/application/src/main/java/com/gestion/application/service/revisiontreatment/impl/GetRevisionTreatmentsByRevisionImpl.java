package com.gestion.application.service.revisiontreatment.impl;

import com.gestion.application.dto.RevisionTreatmentResponse;
import com.gestion.application.exception.RevisionNotFoundException;
import com.gestion.application.mapper.RevisionTreatmentMapper;
import com.gestion.application.repository.RevisionRepository;
import com.gestion.application.repository.RevisionTreatmentRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetRevisionTreatmentsByRevisionImpl {
  private final RevisionTreatmentRepository repo;
  private final RevisionRepository revisionRepo;
  private final RevisionTreatmentMapper mapper;

  public List<RevisionTreatmentResponse> getByRevision(Integer revId) {
    revisionRepo.findById(revId).orElseThrow(() -> new RevisionNotFoundException(revId));
    return repo.findByRevision_IdRevision(revId).stream()
        .map(mapper::toResponse)
        .collect(Collectors.toList());
  }
}
