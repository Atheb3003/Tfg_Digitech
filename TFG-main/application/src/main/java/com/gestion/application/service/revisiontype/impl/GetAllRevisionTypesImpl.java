package com.gestion.application.service.revisiontype.impl;

import com.gestion.application.dto.RevisionTypeResponse;
import com.gestion.application.mapper.RevisionTypeMapper;
import com.gestion.application.repository.RevisionTypeRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllRevisionTypesImpl {
  private final RevisionTypeRepository repo;
  private final RevisionTypeMapper mapper;

  public List<RevisionTypeResponse> getAll() {
    return repo.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
  }
}
