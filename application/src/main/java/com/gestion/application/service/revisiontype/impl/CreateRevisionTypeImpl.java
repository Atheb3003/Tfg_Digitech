package com.gestion.application.service.revisiontype.impl;

import com.gestion.application.dto.RevisionTypeRequest;
import com.gestion.application.dto.RevisionTypeResponse;
import com.gestion.application.exception.RevisionTypeInvalidDataException;
import com.gestion.application.mapper.RevisionTypeMapper;
import com.gestion.application.model.RevisionType;
import com.gestion.application.repository.RevisionTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateRevisionTypeImpl {
  private final RevisionTypeRepository repo;
  private final RevisionTypeMapper mapper;

  public RevisionTypeResponse create(RevisionTypeRequest req) {
    if (req.getTypeName() == null || req.getTypeName().isBlank())
      throw new RevisionTypeInvalidDataException();
    RevisionType saved = repo.save(mapper.toEntity(req));
    return mapper.toResponse(saved);
  }
}
