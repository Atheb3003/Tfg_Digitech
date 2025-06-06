package com.gestion.application.service.revisiontype.impl;

import com.gestion.application.exception.RevisionTypeNotFoundException;
import com.gestion.application.repository.RevisionTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteRevisionTypeImpl {
  private final RevisionTypeRepository repo;

  @Transactional
  public void delete(Integer id) {
    if (!repo.existsById(id)) throw new RevisionTypeNotFoundException(id);
    repo.deleteById(id);
  }
}
