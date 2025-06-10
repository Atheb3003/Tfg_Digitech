// src/main/java/com/gestion/application/service/consultation/impl/UpdateConsultationImpl.java
package com.gestion.application.service.consultation.impl;

import com.gestion.application.dto.ConsultationRequest;
import com.gestion.application.dto.ConsultationResponse;
import com.gestion.application.exception.*;
import com.gestion.application.mapper.ConsultationMapper;
import com.gestion.application.model.Consultation;
import com.gestion.application.model.ConsultationType;
import com.gestion.application.model.Contact;
import com.gestion.application.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateConsultationImpl {

  private final ConsultationRepository repo;
  private final ContactRepository contactRepo;
  private final PatientRepository patientRepo;
  private final ConsultationTypeRepository typeRepo;
  private final ConsultationMapper mapper;

  @Transactional
  public ConsultationResponse update(Integer id, ConsultationRequest req) {
    Consultation existing =
        repo.findById(id).orElseThrow(() -> new ConsultationNotFoundException(id));

    // validar relaciones
    Contact c =
        contactRepo
            .findById(req.getContactId())
            .orElseThrow(() -> new ContactNotFoundException(req.getContactId()));

    ConsultationType t =
        typeRepo
            .findById(req.getTypeId())
            .orElseThrow(() -> new ConsultationTypeNotFoundException(req.getTypeId()));

    // actualizar campos
    existing.setContact(c);
    existing.setType(t);
    existing.setFollicularUnits(req.getFollicularUnits());
    existing.setInsertionZones(req.getInsertionZones());
    existing.setObservations(req.getObservations());
    existing.setTreatmentDone(req.getTreatmentDone());
    existing.setConsultationDate(req.getConsultationDate());
    // isVisible se mantiene

    Consultation saved = repo.save(existing);
    return mapper.toResponse(saved);
  }
}
