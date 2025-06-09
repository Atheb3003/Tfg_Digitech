package com.gestion.application.service.protocol.impl;

import com.gestion.application.dto.SuccessfulUpdateResponse;
import com.gestion.application.exception.ProtocolNotFoundException;
import com.gestion.application.model.Protocol;
import com.gestion.application.repository.ProtocolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompleteProtocolIfAllTreatmentsFinishedImpl {

    private final ProtocolRepository repository;

    public SuccessfulUpdateResponse markAsFinishedIfApplicable(Integer idProtocol) {
        Protocol protocol = repository.findById(idProtocol)
                .orElseThrow(() -> new ProtocolNotFoundException(idProtocol));

        boolean allFinished = protocol.getTreatments().stream()
                .allMatch(t -> Boolean.TRUE.equals(t.getIsFinished()));

        if (allFinished && !Boolean.TRUE.equals(protocol.getIsFinished())) {
            protocol.setIsFinished(true);
            repository.save(protocol);
            return new SuccessfulUpdateResponse("success", "Protocolo marcado como finalizado");
        }

        return new SuccessfulUpdateResponse("ignored", "No se actualizó: tratamientos incompletos");
    }
}
