package com.gestion.application.service.revisiontype.impl;

import com.gestion.application.dto.RevisionTypeResponse;
import com.gestion.application.exception.RevisionTypeNotFoundException;
import com.gestion.application.mapper.RevisionTypeMapper;
import com.gestion.application.model.RevisionType;
import com.gestion.application.repository.RevisionTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetRevisionTypeByIdImpl {
  private final RevisionTypeRepository repo;
  private final RevisionTypeMapper mapper;

  public RevisionTypeResponse getById(Integer id) {
    RevisionType e = repo.findById(id).orElseThrow(() -> new RevisionTypeNotFoundException(id));
    return mapper.toResponse(e);
  }
}
