package com.gestion.application.service.consultation.impl;

import com.gestion.application.exception.ConsultationAlreadyVisibleException;
import com.gestion.application.exception.ConsultationNotFoundException;
import com.gestion.application.model.Consultation;
import com.gestion.application.repository.ConsultationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MakeConsultationVisibleImpl {

  private final ConsultationRepository repo;

  @Transactional
  public Consultation setVisible(Integer id) {
    Consultation c = repo.findById(id).orElseThrow(() -> new ConsultationNotFoundException(id));
    if (Boolean.TRUE.equals(c.getIsVisible())) {
      throw new ConsultationAlreadyVisibleException(id);
    }
    c.setIsVisible(true);
    return repo.save(c);
  }
}
