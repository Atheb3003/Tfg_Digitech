package com.gestion.application.service.revision.impl;

import com.gestion.application.exception.RevisionNotFoundException;
import com.gestion.application.model.Revision;
import com.gestion.application.repository.RevisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Implementación de la operación “eliminar físicamente” (borrado real). */
@Service
@RequiredArgsConstructor
public class DeleteRevisionImpl {

  private final RevisionRepository revisionRepository;

  @Transactional
  public void execute(Integer id) {
    Revision existing =
        revisionRepository.findById(id).orElseThrow(() -> new RevisionNotFoundException(id));
    revisionRepository.delete(existing);
  }
}
