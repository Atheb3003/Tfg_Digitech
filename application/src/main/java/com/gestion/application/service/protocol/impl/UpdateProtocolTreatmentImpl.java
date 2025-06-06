package com.gestion.application.service.protocol.impl;

import com.gestion.application.model.Protocol;
import com.gestion.application.model.ProtocolTreatment;
import com.gestion.application.repository.ProtocolRepository;
import com.gestion.application.repository.ProtocolTreatmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateProtocolTreatmentImpl {

  private final ProtocolTreatmentRepository protocolTreatmentRepository;
  private final ProtocolRepository protocolRepository;

  public void markTreatmentAsFinished(Integer treatmentId) {
    ProtocolTreatment treatment =
        protocolTreatmentRepository
            .findById(treatmentId)
            .orElseThrow(() -> new RuntimeException("Tratamiento no encontrado"));

    treatment.setIsFinished(true);
    protocolTreatmentRepository.save(treatment);

    Protocol protocol = treatment.getProtocol();

    boolean allFinished =
        protocol.getTreatments().stream().allMatch(ProtocolTreatment::getIsFinished);

    if (allFinished && !protocol.getIsFinished()) {
      protocol.setIsFinished(true);
      protocolRepository.save(protocol);
    }
  }
}
