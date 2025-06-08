package com.gestion.application.service.patient.impl;

import com.gestion.application.dto.PatientResponse;
import com.gestion.application.exception.PatientNotFoundException;
import com.gestion.application.mapper.PatientMapper;
import com.gestion.application.model.Patient;
import com.gestion.application.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPatientByIdImpl {

  private final PatientRepository repository;
  private final PatientMapper mapper;

  public PatientResponse getPatientById(Integer id) {

    if (!repository.existsById(id)) {
      throw new PatientNotFoundException(id);
    }

    Patient entity = repository.findById(id).get();
    return mapper.toResponse(entity);
  }
}
