package com.gestion.application.service.revision.impl;

import com.gestion.application.dto.RevisionResponse;
import com.gestion.application.mapper.RevisionMapper;
import com.gestion.application.repository.RevisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de la operación “listar solo revisiones visibles”.
 */
@Service
@RequiredArgsConstructor
public class GetVisibleRevisionsImpl {

    private final RevisionRepository revisionRepository;
    private final RevisionMapper revisionMapper;

    @Transactional(readOnly = true)
    public List<RevisionResponse> execute() {
        return revisionRepository.findAllByIsVisibleTrue().stream()
                .map(revisionMapper::toDto)
                .collect(Collectors.toList());
    }
}
