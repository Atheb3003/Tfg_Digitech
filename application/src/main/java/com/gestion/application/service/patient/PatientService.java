package com.gestion.application.service.patient;

import com.gestion.application.dto.PatientResponse;

public interface PatientService {
  /** GET /patients/{id} */
  PatientResponse getPatientById(Integer id);
}
