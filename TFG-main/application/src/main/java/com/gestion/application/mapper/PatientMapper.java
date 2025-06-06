package com.gestion.application.mapper;

import com.gestion.application.dto.PatientResponse;
import com.gestion.application.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

  /** Convierte entidad a DTO */
  public PatientResponse toResponse(Patient entity) {
    PatientResponse dto = new PatientResponse();
    dto.setIdPatient(entity.getIdPatient());
    dto.setIdContact(entity.getContact().getIdContact());
    dto.setDischargeDate(entity.getDischargeDate());
    dto.setIsVisible(entity.getIsVisible());
    return dto;
  }
}
