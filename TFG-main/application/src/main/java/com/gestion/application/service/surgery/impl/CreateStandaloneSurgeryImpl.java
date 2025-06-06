package com.gestion.application.service.surgery.impl;

import com.gestion.application.dto.CreateSurgeryRequest;
import com.gestion.application.model.Surgery;
import com.gestion.application.repository.SurgeryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateStandaloneSurgeryImpl {

  private final SurgeryRepository surgeryRepository;

  public Surgery create(CreateSurgeryRequest request) {
    Surgery surgery = new Surgery();
    surgery.setDate(request.getDate());
    surgery.setObservations(request.getObservations());
    surgery.setIsVisible(true);
    // No hay reservation asociada
    return surgeryRepository.save(surgery);
  }
}
