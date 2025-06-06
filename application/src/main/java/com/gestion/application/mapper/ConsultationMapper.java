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

  public Consultation toEntity(ConsultationRequest dto) {
    Consultation c = new Consultation();
    // Asociar entidades por ID
    Contact contact = new Contact();
    contact.setIdContact(dto.getContactId());
    Patient patient = new Patient();
    patient.setIdPatient(dto.getPatientId());
    ConsultationType type = new ConsultationType();
    type.setIdType(dto.getTypeId());

    c.setContact(contact);
    c.setPatient(patient);
    c.setType(type);
    c.setFollicularUnits(dto.getFollicularUnits());
    c.setInsertionZones(dto.getInsertionZones());
    c.setObservations(dto.getObservations());
    c.setTreatmentDone(dto.getTreatmentDone());
    c.setConsultationDate(dto.getConsultationDate());
    c.setIsVisible(true);
    return c;
  }

  public ConsultationResponse toResponse(Consultation entity) {
    ConsultationResponse dto = new ConsultationResponse();
    dto.setIdConsultation(entity.getIdConsultation());
    dto.setContactId(entity.getContact().getIdContact());
    dto.setPatientId(entity.getPatient().getIdPatient());
    dto.setTypeId(entity.getType().getIdType());
    dto.setFollicularUnits(entity.getFollicularUnits());
    dto.setInsertionZones(entity.getInsertionZones());
    dto.setObservations(entity.getObservations());
    dto.setTreatmentDone(entity.getTreatmentDone());
    dto.setConsultationDate(entity.getConsultationDate());
    dto.setIsVisible(entity.getIsVisible());
    return dto;
  }
}
