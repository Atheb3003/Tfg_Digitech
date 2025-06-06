package com.gestion.application.service.revisiontreatment.impl;

import com.gestion.application.exception.RevisionTreatmentNotFoundException;
import com.gestion.application.repository.RevisionTreatmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteRevisionTreatmentImpl {
  private final RevisionTreatmentRepository repo;

  @Transactional
  public void delete(Integer id) {
    if (!repo.existsById(id)) {
      throw new RevisionTreatmentNotFoundException(id);
    }
    repo.deleteById(id);
  }
}
