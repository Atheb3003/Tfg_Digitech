package com.gestion.application.service.consultation.impl;

import com.gestion.application.exception.ConsultationNotFoundException;
import com.gestion.application.repository.ConsultationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteConsultationImpl {

  private final ConsultationRepository repo;

  @Transactional
  public void delete(Integer id) {
    if (!repo.existsById(id)) {
      throw new ConsultationNotFoundException(id);
    }
    repo.deleteById(id);
  }
}
