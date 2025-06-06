package com.gestion.application.service.revision.impl;

import com.gestion.application.dto.RevisionResponse;
import com.gestion.application.exception.RevisionNotFoundException;
import com.gestion.application.mapper.RevisionMapper;
import com.gestion.application.model.Revision;
import com.gestion.application.repository.RevisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de la operación “marcar revisión como visible”.
 */
@Service
@RequiredArgsConstructor
public class SetRevisionVisibleImpl {

    private final RevisionRepository revisionRepository;
    private final RevisionMapper revisionMapper;

    @Transactional
    public RevisionResponse execute(Integer id) {
        Revision existing = revisionRepository.findById(id)
                .orElseThrow(() -> new RevisionNotFoundException(id));
        existing.setIsVisible(true);
        Revision saved = revisionRepository.save(existing);
        return revisionMapper.toDto(saved);
    }
}
