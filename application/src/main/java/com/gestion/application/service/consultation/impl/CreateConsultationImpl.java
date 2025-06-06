package com.gestion.application.service.consultation.impl;

import com.gestion.application.dto.ConsultationRequest;
import com.gestion.application.dto.ConsultationResponse;
import com.gestion.application.exception.ConsultationCreationException;
import com.gestion.application.exception.ContactNotFoundException;
import com.gestion.application.exception.PatientNotFoundException;
import com.gestion.application.exception.ProtocolNotFoundException; // si usas el mismo para type
import com.gestion.application.mapper.ConsultationMapper;
import com.gestion.application.model.Consultation;
import com.gestion.application.repository.ConsultationRepository;
import com.gestion.application.repository.ConsultationTypeRepository;
import com.gestion.application.repository.ContactRepository;
import com.gestion.application.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateConsultationImpl {

  private final ConsultationRepository repo;
  private final ContactRepository contactRepo;
  private final PatientRepository patientRepo;
  private final ConsultationTypeRepository typeRepo;
  private final ConsultationMapper mapper;

  @Transactional
  public ConsultationResponse create(ConsultationRequest req) {
    // Validar relaciones
    contactRepo
        .findById(req.getContactId())
        .orElseThrow(() -> new ContactNotFoundException(req.getContactId()));
    patientRepo
        .findById(req.getPatientId())
        .orElseThrow(() -> new PatientNotFoundException(req.getPatientId()));
    typeRepo
        .findById(req.getTypeId())
        .orElseThrow(() -> new ProtocolNotFoundException(req.getTypeId()));

    try {
      Consultation saved = repo.save(mapper.toEntity(req));
      return mapper.toResponse(saved);
    } catch (Exception ex) {
      throw new ConsultationCreationException();
    }
  }
}
