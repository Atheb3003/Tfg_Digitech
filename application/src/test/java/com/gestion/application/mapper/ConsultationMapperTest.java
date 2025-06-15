package com.gestion.application.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.gestion.application.dto.ConsultationRequest;
import com.gestion.application.dto.ConsultationResponse;
import com.gestion.application.model.Consultation;
import com.gestion.application.model.ConsultationType;
import com.gestion.application.model.Contact;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class ConsultationMapperTest {

  private final ConsultationMapper mapper = new ConsultationMapper();

  @Test
  void testToEntity() {
    ConsultationRequest request = new ConsultationRequest();
    request.setContactId(10);
    request.setTypeId(5);
    request.setFollicularUnits(300);
    request.setInsertionZones("Zona frontal");
    request.setObservations("Paciente en buen estado");
    request.setTreatmentDone(true);
    request.setSurgeryReserved(false);
    request.setConsultationDate(LocalDate.of(2025, 6, 14));

    Consultation entity = mapper.toEntity(request);

    assertNotNull(entity.getContact());
    assertEquals(10, entity.getContact().getIdContact());

    assertNotNull(entity.getType());
    assertEquals(5, entity.getType().getIdType());

    assertEquals(300, entity.getFollicularUnits());
    assertEquals("Zona frontal", entity.getInsertionZones());
    assertEquals("Paciente en buen estado", entity.getObservations());
    assertTrue(entity.getTreatmentDone());
    assertFalse(entity.getSurgeryReserved());
    assertEquals(LocalDate.of(2025, 6, 14), entity.getConsultationDate());
    assertTrue(entity.getIsVisible());
  }

  @Test
  void testToResponse() {
    Contact contact = new Contact();
    contact.setIdContact(10);
    contact.setName("Juan");
    contact.setSurname("Pérez");

    ConsultationType type = new ConsultationType();
    type.setIdType(5);
    type.setTypeName("Consulta inicial");

    Consultation entity = new Consultation();
    entity.setIdConsultation(100);
    entity.setContact(contact);
    entity.setType(type);
    entity.setFollicularUnits(300);
    entity.setInsertionZones("Zona frontal");
    entity.setObservations("Observaciones");
    entity.setTreatmentDone(true);
    entity.setSurgeryReserved(true);
    entity.setConsultationDate(LocalDate.of(2025, 6, 14));
    entity.setIsVisible(true);

    ConsultationResponse response = mapper.toResponse(entity);

    assertEquals(100, response.getIdConsultation());
    assertEquals(10, response.getContactId());
    assertEquals(5, response.getIdType());
    assertEquals(300, response.getFollicularUnits());
    assertEquals("Zona frontal", response.getInsertionZones());
    assertEquals("Observaciones", response.getObservations());
    assertTrue(response.getTreatmentDone());
    assertTrue(response.getSurgeryReserved());
    assertEquals(LocalDate.of(2025, 6, 14), response.getConsultationDate());
    assertTrue(response.getIsVisible());
    assertEquals("Juan Pérez", response.getNombreContacto());
    assertEquals("Consulta inicial", response.getType());
  }
}
