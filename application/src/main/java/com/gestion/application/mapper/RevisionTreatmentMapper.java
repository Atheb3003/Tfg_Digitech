package com.gestion.application.mapper;

import com.gestion.application.dto.RevisionTreatmentRequest;
import com.gestion.application.dto.RevisionTreatmentResponse;
import com.gestion.application.model.Product;
import com.gestion.application.model.Revision;
import com.gestion.application.model.RevisionTreatment;
import org.springframework.stereotype.Component;

@Component
public class RevisionTreatmentMapper {

  public RevisionTreatment toEntity(RevisionTreatmentRequest dto) {
    RevisionTreatment rt = new RevisionTreatment();
    Revision r = new Revision();
    // Usar setIdRevison, no setIdRevision
    r.setIdRevision(dto.getRevisionId());
    Product p = new Product();
    p.setIdProduct(dto.getProductId());
    rt.setRevision(r);
    rt.setProduct(p);
    return rt;
  }

  public RevisionTreatmentResponse toResponse(RevisionTreatment entity) {
    RevisionTreatmentResponse dto = new RevisionTreatmentResponse();
    dto.setId(entity.getId());
    // Usar getIdRevison, no getIdRevision
    dto.setRevisionId(entity.getRevision().getIdRevision());
    dto.setProductId(entity.getProduct().getIdProduct());
    return dto;
  }
}
