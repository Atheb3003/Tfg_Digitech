package com.gestion.application.service.revision.impl;

import com.gestion.application.dto.RevisionResponse;
import com.gestion.application.mapper.RevisionMapper;
import com.gestion.application.repository.RevisionRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Implementación de la operación “listar revisiones por ID de contacto”. */
@Service
@RequiredArgsConstructor
public class GetContactRevisionsImpl {

  private final RevisionRepository revisionRepository;
  private final RevisionMapper revisionMapper;

  @Transactional(readOnly = true)
  public List<RevisionResponse> execute(Integer contactId) {
    return revisionRepository.findByContact_IdContact(contactId).stream()
        .map(revisionMapper::toDto)
        .collect(Collectors.toList());
  }
}
