// src/main/java/com/gestion/application/service/consultation/impl/GetConsultationsByContactImpl.java
package com.gestion.application.service.consultation.impl;

import com.gestion.application.dto.ConsultationResponse;
import com.gestion.application.exception.ContactNotFoundException;
import com.gestion.application.mapper.ConsultationMapper;
import com.gestion.application.repository.ConsultationRepository;
import com.gestion.application.repository.ContactRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetConsultationsByContactImpl {

  private final ConsultationRepository repo;
  private final ContactRepository contactRepo;
  private final ConsultationMapper mapper;

  /** → 404 si el contacto no existe */
  public List<ConsultationResponse> getByContact(Integer contactId) {
    contactRepo.findById(contactId).orElseThrow(() -> new ContactNotFoundException(contactId));

    return repo.findByContact_IdContact(contactId).stream()
        .map(mapper::toResponse)
        .collect(Collectors.toList());
  }
}
