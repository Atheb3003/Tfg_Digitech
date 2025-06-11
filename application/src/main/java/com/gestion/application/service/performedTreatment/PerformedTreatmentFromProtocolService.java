package com.gestion.application.service.performedTreatment;

import com.gestion.application.dto.PerformedTreatmentFromProtocolRequest;
import com.gestion.application.model.Contact;
import com.gestion.application.model.PerformedTreatment;
import com.gestion.application.model.Revision;
import com.gestion.application.repository.ContactRepository;
import com.gestion.application.repository.PerformedTreatmentRepository;
import com.gestion.application.repository.RevisionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PerformedTreatmentFromProtocolService {

    private final PerformedTreatmentRepository performedTreatmentRepository;
    private final ContactRepository contactRepository;
    private final RevisionRepository revisionRepository;

    @Transactional
    public void createFromProtocol(PerformedTreatmentFromProtocolRequest request) {
        // Solo validamos el contacto porque sí se relaciona como entidad
        Contact contact = contactRepository.findById(request.getContactId())
                .orElseThrow(() -> new RuntimeException("Contacto no encontrado"));

        PerformedTreatment performed = new PerformedTreatment();
        performed.setContact(contact);
        performed.setPerformedDate(request.getPerformedDate());
        performed.setFinalPrice(request.getFinalPrice());
        performed.setNotes(request.getNotes());
        performed.setCreationDate(LocalDate.now());
        performed.setProtocolTreatmentId(request.getProtocolTreatmentId());

        // Si hay revisión, la buscamos
        if (request.getRevisionId() != null) {
            Revision revision = revisionRepository.findById(request.getRevisionId())
                    .orElseThrow(() -> new RuntimeException("Revisión no encontrada"));
            performed.setRevision(revision);
        }

        performedTreatmentRepository.save(performed);
    }
}
