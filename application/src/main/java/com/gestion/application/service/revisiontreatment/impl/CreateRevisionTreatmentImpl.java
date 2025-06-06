package com.gestion.application.service.revisiontreatment.impl;

import com.gestion.application.dto.RevisionTreatmentRequest;
import com.gestion.application.dto.RevisionTreatmentResponse;
import com.gestion.application.exception.*;
import com.gestion.application.mapper.RevisionTreatmentMapper;
import com.gestion.application.model.RevisionTreatment;
import com.gestion.application.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateRevisionTreatmentImpl {
  private final RevisionTreatmentRepository repo;
  private final RevisionRepository revisionRepo;
  private final ProductRepository productRepo;
  private final RevisionTreatmentMapper mapper;

  public RevisionTreatmentResponse create(RevisionTreatmentRequest req) {
    if (req.getRevisionId() == null || req.getProductId() == null) {
      throw new RevisionTreatmentInvalidDataException();
    }
    revisionRepo
        .findById(req.getRevisionId())
        .orElseThrow(() -> new RevisionNotFoundException(req.getRevisionId()));
    productRepo
        .findById(req.getProductId())
        .orElseThrow(() -> new ProductNotFoundException(req.getProductId()));

    RevisionTreatment saved;
    try {
      saved = repo.save(mapper.toEntity(req));
    } catch (Exception ex) {
      throw new RevisionTreatmentCreationException("Error al crear tratamiento de revisión.");
    }
    return mapper.toResponse(saved);
  }
}
