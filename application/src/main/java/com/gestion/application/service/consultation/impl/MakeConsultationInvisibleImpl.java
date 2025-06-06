package com.gestion.application.service.consultation.impl;

import com.gestion.application.exception.ConsultationAlreadyInvisibleException;
import com.gestion.application.exception.ConsultationNotFoundException;
import com.gestion.application.model.Consultation;
import com.gestion.application.repository.ConsultationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MakeConsultationInvisibleImpl {

  private final ConsultationRepository repo;

  @Transactional
  public Consultation setInvisible(Integer id) {
    Consultation c = repo.findById(id).orElseThrow(() -> new ConsultationNotFoundException(id));
    if (Boolean.FALSE.equals(c.getIsVisible())) {
      throw new ConsultationAlreadyInvisibleException(id);
    }
    c.setIsVisible(false);
    return repo.save(c);
  }
}
