package com.gestion.application.service.consultation;

import com.gestion.application.dto.ConsultationRequest;
import com.gestion.application.dto.ConsultationResponse;
import com.gestion.application.model.Consultation;
import com.gestion.application.service.consultation.impl.*;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsultationService {

  private final CreateConsultationImpl createImpl;
  private final GetAllConsultationsImpl allImpl;
  private final GetVisibleConsultationsImpl visibleImpl;
  //  private final GetConsultationsByPatientImpl byPatientImpl;
  private final DeleteConsultationImpl deleteImpl;
  private final MakeConsultationVisibleImpl showImpl;
  private final MakeConsultationInvisibleImpl hideImpl;
  private final com.gestion.application.mapper.ConsultationMapper mapper;
  private final GetConsultationsByContactImpl byContactImpl;
  private final GetConsultationsByTypeImpl byTypeImpl;
  private final UpdateConsultationImpl updateImpl;

  public ConsultationResponse create(ConsultationRequest req) {
    return createImpl.create(req);
  }

  public List<ConsultationResponse> getAll() {
    return allImpl.getAll();
  }

  public List<ConsultationResponse> getVisible() {
    return visibleImpl.getVisible();
  }

  //  public List<ConsultationResponse> getByPatient(Integer patientId) {
  //    return byPatientImpl.getByPatient(patientId);
  //  }

  public void delete(Integer id) {
    deleteImpl.delete(id);
  }

  public ConsultationResponse makeVisible(Integer id) {
    Consultation c = showImpl.setVisible(id);
    return mapper.toResponse(c);
  }

  public ConsultationResponse makeInvisible(Integer id) {
    Consultation c = hideImpl.setInvisible(id);
    return mapper.toResponse(c);
  }

  /** GET /consultations/contact/{id} */
  public List<ConsultationResponse> getByContact(Integer contactId) {
    return byContactImpl.getByContact(contactId);
  }

  /** PUT /consultations/{id} */
  public ConsultationResponse updateConsultation(Integer id, ConsultationRequest req) {
    return updateImpl.update(id, req);
  }

  /** GET /consultations/type/{id} */
  public List<ConsultationResponse> getByType(Integer typeId) {
    return byTypeImpl.getByType(typeId);
  }
}
