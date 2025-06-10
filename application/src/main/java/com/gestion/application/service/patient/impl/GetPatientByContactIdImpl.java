package com.gestion.application.service.patient.impl;

import com.gestion.application.model.Patient;
import com.gestion.application.repository.PatientRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPatientByContactIdImpl {

  private final PatientRepository patientRepository;

  public Optional<Integer> getPatientIdByContact(Integer contactId) {
    return patientRepository.findByContact_IdContact(contactId).map(Patient::getIdPatient);
  }
}
