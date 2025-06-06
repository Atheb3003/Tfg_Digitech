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
 * Implementación de la operación “listar revisiones por ID de tipo de revisión”.
 */
@Service
@RequiredArgsConstructor
public class GetRevisionsByTypeImpl {

    private final RevisionRepository revisionRepository;
    private final RevisionMapper revisionMapper;

    @Transactional(readOnly = true)
    public List<RevisionResponse> execute(Integer typeId) {
        return revisionRepository.findByType_IdRevisionType(typeId).stream()
                .map(revisionMapper::toDto)
                .collect(Collectors.toList());
    }
}
