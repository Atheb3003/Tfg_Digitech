package com.gestion.application.mapper;

import com.gestion.application.dto.CreateRevisionRequest;
import com.gestion.application.dto.RevisionResponse;
import com.gestion.application.model.Revision;
import org.springframework.stereotype.Component;

/** Mapper que convierte entre entidad Revision y DTOs (create/update y response). */
@Component
public class RevisionMapper {

  /**
   * Convierte de DTO a entidad (solo campos simples). Relaciones (contact, type, protocol,
   * idPatient) se asignan en el servicio.
   */
  public Revision toEntity(CreateRevisionRequest dto) {
    Revision rev = new Revision();
    rev.setObservations(dto.getObservations());
    rev.setRevisionDate(dto.getRevisionDate());
    // contact, type, protocol, idPatient se establecen luego
    return rev;
  }

  /** Convierte de entidad Revision a DTO de respuesta, incluyendo todos los campos. */
  public RevisionResponse toDto(Revision entity) {
    RevisionResponse resp = new RevisionResponse();
    resp.setIdRevision(entity.getIdRevision());
    resp.setIdContact(entity.getContact().getIdContact());
    resp.setIdType(entity.getType().getIdRevisionType());
    resp.setObservations(entity.getObservations());
    resp.setRevisionDate(entity.getRevisionDate());
    resp.setIdProtocol(entity.getProtocol() != null ? entity.getProtocol().getIdProtocol() : null);
    resp.setIdPatient(entity.getIdPatient());
    resp.setIsVisible(entity.getIsVisible());
    return resp;
  }
}
