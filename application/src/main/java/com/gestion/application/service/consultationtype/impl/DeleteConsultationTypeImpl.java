package com.gestion.application.service.consultationtype.impl;

import com.gestion.application.exception.ConsultationTypeNotFoundException;
import com.gestion.application.repository.ConsultationTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteConsultationTypeImpl {

  private final ConsultationTypeRepository repo;

  @Transactional
  public void delete(Integer id) {
    if (!repo.existsById(id)) {
      throw new ConsultationTypeNotFoundException(id);
    }
    repo.deleteById(id);
  }
}
