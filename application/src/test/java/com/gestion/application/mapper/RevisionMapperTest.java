package com.gestion.application.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.gestion.application.dto.CreateRevisionRequest;
import com.gestion.application.dto.RevisionResponse;
import com.gestion.application.model.Contact;
import com.gestion.application.model.Protocol;
import com.gestion.application.model.Revision;
import com.gestion.application.model.RevisionType;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class RevisionMapperTest {

  private final RevisionMapper mapper = new RevisionMapper();

  @Test
  void testToEntity() {
    CreateRevisionRequest request = new CreateRevisionRequest();
    request.setObservations("Observaciones importantes");
    request.setRevisionDate(LocalDate.of(2025, 6, 14));
    request.setIdContact(1);
    request.setIdType(2);
    request.setIdProtocol(3);
    request.setIdPatient(4);

    Revision entity = mapper.toEntity(request);

    assertEquals("Observaciones importantes", entity.getObservations());
    assertEquals(LocalDate.of(2025, 6, 14), entity.getRevisionDate());
    // contact, type, protocol, idPatient se asignan en el servicio, no en el mapper
    assertNull(entity.getContact());
    assertNull(entity.getType());
    assertNull(entity.getProtocol());
    assertNull(entity.getIdPatient());
  }

  @Test
  void testToDto() {
    Contact contact = new Contact();
    contact.setIdContact(10);

    RevisionType type = new RevisionType();
    type.setIdRevisionType(20);

    Protocol protocol = new Protocol();
    protocol.setIdProtocol(30);

    Revision entity = new Revision();
    entity.setIdRevision(100);
    entity.setContact(contact);
    entity.setType(type);
    entity.setObservations("Detalle revisión");
    entity.setRevisionDate(LocalDate.of(2025, 6, 14));
    entity.setProtocol(protocol);
    entity.setIdPatient(40);
    entity.setIsVisible(true);

    RevisionResponse response = mapper.toDto(entity);

    assertEquals(100, response.getIdRevision());
    assertEquals(10, response.getIdContact());
    assertEquals(20, response.getIdType());
    assertEquals("Detalle revisión", response.getObservations());
    assertEquals(LocalDate.of(2025, 6, 14), response.getRevisionDate());
    assertEquals(30, response.getIdProtocol());
    assertEquals(40, response.getIdPatient());
    assertTrue(response.getIsVisible());
  }
}
