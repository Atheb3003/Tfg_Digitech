package com.gestion.application.service.revisiontreatment;

import com.gestion.application.dto.RevisionTreatmentRequest;
import com.gestion.application.dto.RevisionTreatmentResponse;
import com.gestion.application.service.revisiontreatment.impl.*;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RevisionTreatmentService {

  private final CreateRevisionTreatmentImpl createImpl;
  private final GetAllRevisionTreatmentsImpl allImpl;
  private final GetRevisionTreatmentsByRevisionImpl byRevImpl;
  private final DeleteRevisionTreatmentImpl deleteImpl;

  public RevisionTreatmentResponse create(RevisionTreatmentRequest req) {
    return createImpl.create(req);
  }

  public List<RevisionTreatmentResponse> getAll() {
    return allImpl.getAll();
  }

  public List<RevisionTreatmentResponse> getByRevision(Integer revId) {
    return byRevImpl.getByRevision(revId);
  }

  public void delete(Integer id) {
    deleteImpl.delete(id);
  }
}
