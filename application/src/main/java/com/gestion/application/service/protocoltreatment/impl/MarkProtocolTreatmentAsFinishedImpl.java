package com.gestion.application.service.protocoltreatment.impl;

import com.gestion.application.dto.SuccessfulMarkAsPaidResponse;
import com.gestion.application.exception.ProtocolTreatmentNotFoundException;
import com.gestion.application.model.ProtocolTreatment;
import com.gestion.application.repository.ProtocolTreatmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarkProtocolTreatmentAsFinishedImpl {

  private final ProtocolTreatmentRepository repository;

  public SuccessfulMarkAsPaidResponse markAsFinished(Integer id) {
    ProtocolTreatment treatment =
        repository.findById(id).orElseThrow(() -> new ProtocolTreatmentNotFoundException(id));

    treatment.setIsFinished(true);
    repository.save(treatment);

    return new SuccessfulMarkAsPaidResponse(
        "success", "Tratamiento con ID " + id + " marcado como realizado");
  }
}
