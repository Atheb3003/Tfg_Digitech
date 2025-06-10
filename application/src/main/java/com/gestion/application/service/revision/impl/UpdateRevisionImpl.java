package com.gestion.application.service.revision.impl;

import com.gestion.application.dto.CreateRevisionRequest;
import com.gestion.application.dto.RevisionResponse;
import com.gestion.application.exception.*;
import com.gestion.application.mapper.RevisionMapper;
import com.gestion.application.model.Contact;
import com.gestion.application.model.Protocol;
import com.gestion.application.model.Revision;
import com.gestion.application.model.RevisionType;
import com.gestion.application.repository.ContactRepository;
import com.gestion.application.repository.PatientRepository;
import com.gestion.application.repository.ProtocolRepository;
import com.gestion.application.repository.RevisionRepository;
import com.gestion.application.repository.RevisionTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Implementación de la operación “actualizar revisión”. */
@Service
@RequiredArgsConstructor
public class UpdateRevisionImpl {

  private final RevisionRepository revisionRepository;
  private final ContactRepository contactRepository;
  private final PatientRepository patientRepository;
  private final RevisionTypeRepository revisionTypeRepository;
  private final ProtocolRepository protocolRepository;
  private final RevisionMapper revisionMapper;

  @Transactional
  public RevisionResponse execute(Integer id, CreateRevisionRequest req) {
    // 1. Buscar entidad existente
    Revision existing =
        revisionRepository.findById(id).orElseThrow(() -> new RevisionNotFoundException(id));

    // 2. Validar campos obligatorios
    if (req.getIdContact() == null
        || req.getIdType() == null
        || req.getObservations() == null
        || req.getObservations().trim().isEmpty()
        || req.getRevisionDate() == null) {
      throw new RevisionInvalidDataException(
          "Faltan datos obligatorios en la petición de actualización");
    }

    // 3. Buscar Contact
    Contact contact =
        contactRepository
            .findById(req.getIdContact())
            .orElseThrow(() -> new ContactNotFoundException(req.getIdContact()));

    // 4. Buscar RevisionType
    RevisionType type =
        revisionTypeRepository
            .findById(req.getIdType())
            .orElseThrow(() -> new RevisionTypeNotFoundException(req.getIdType()));

    // 5. Buscar Protocol (si idProtocol != null)
    Protocol protocol = null;
    if (req.getIdProtocol() != null) {
      protocol =
          protocolRepository
              .findById(req.getIdProtocol())
              .orElseThrow(() -> new ProtocolNotFoundException(req.getIdProtocol()));
    }

    // 6. Buscar Patient (si idPatient != null)
    Integer idPac = req.getIdPatient();
    if (idPac != null) {
      patientRepository.findById(idPac).orElseThrow(() -> new PatientNotFoundException(idPac));
    }

    // 7. Asignar valores a la entidad existente
    existing.setContact(contact);
    existing.setType(type);
    existing.setProtocol(protocol);
    existing.setObservations(req.getObservations());
    existing.setRevisionDate(req.getRevisionDate());
    existing.setIdPatient(idPac);

    // 8. Guardar cambios
    Revision updated = revisionRepository.save(existing);
    return revisionMapper.toDto(updated);
  }
}
