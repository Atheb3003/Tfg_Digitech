package com.gestion.application.service.revisiontype.impl;

import com.gestion.application.dto.RevisionTypeRequest;
import com.gestion.application.dto.RevisionTypeResponse;
import com.gestion.application.exception.*;
import com.gestion.application.mapper.RevisionTypeMapper;
import com.gestion.application.model.RevisionType;
import com.gestion.application.repository.RevisionTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateRevisionTypeImpl {
  private final RevisionTypeRepository repo;
  private final RevisionTypeMapper mapper;

  @Transactional
  public RevisionTypeResponse update(Integer id, RevisionTypeRequest req) {
    RevisionType existing =
        repo.findById(id).orElseThrow(() -> new RevisionTypeNotFoundException(id));
    if (req.getTypeName() == null || req.getTypeName().isBlank())
      throw new RevisionTypeInvalidDataException();
    existing.setTypeName(req.getTypeName());
    return mapper.toResponse(repo.save(existing));
  }
}
