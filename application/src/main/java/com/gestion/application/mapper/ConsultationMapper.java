package com.gestion.application.mapper;

import com.gestion.application.dto.ConsultationRequest;
import com.gestion.application.dto.ConsultationResponse;
import com.gestion.application.model.Consultation;
import com.gestion.application.model.ConsultationType;
import com.gestion.application.model.Contact;
import com.gestion.application.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class ConsultationMapper {

  /** Convierte DTO de petición a entidad */
  public Consultation toEntity(ConsultationRequest dto) {
    Consultation c = new Consultation();

    // Asociar entidades por ID
    Contact contact = new Contact();
    contact.setIdContact(dto.getContactId());


    ConsultationType type = new ConsultationType();
    type.setIdType(dto.getTypeId());

    c.setContact(contact);;
    c.setType(type);
    c.setFollicularUnits(dto.getFollicularUnits());
    c.setInsertionZones(dto.getInsertionZones());
    c.setObservations(dto.getObservations());
    c.setTreatmentDone(dto.getTreatmentDone());
    c.setSurgeryReserved(dto.getSurgeryReserved()); // 👈 NUEVO
    c.setConsultationDate(dto.getConsultationDate());
    c.setIsVisible(true);

    return c;
  }

  /** Convierte entidad a DTO de respuesta */
  public ConsultationResponse toResponse(Consultation entity) {
    ConsultationResponse dto = new ConsultationResponse();

    dto.setIdConsultation(entity.getIdConsultation());
    dto.setContactId(entity.getContact().getIdContact());
    dto.setFollicularUnits(entity.getFollicularUnits());
    dto.setInsertionZones(entity.getInsertionZones());
    dto.setObservations(entity.getObservations());
    dto.setTreatmentDone(entity.getTreatmentDone());
    dto.setSurgeryReserved(entity.getSurgeryReserved()); // 👈 NUEVO
    dto.setConsultationDate(entity.getConsultationDate());
    dto.setIsVisible(entity.getIsVisible());
    dto.setIdType(entity.getType().getIdType());
    dto.setNombreContacto(entity.getContact().getName() + " " + entity.getContact().getSurname());
    dto.setType(entity.getType().getTypeName());

    return dto;
  }
}
