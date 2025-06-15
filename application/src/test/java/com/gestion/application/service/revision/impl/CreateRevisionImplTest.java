package com.gestion.application.service.revision.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.CreateRevisionRequest;
import com.gestion.application.exception.*;
import com.gestion.application.mapper.RevisionMapper;
import com.gestion.application.model.Contact;
import com.gestion.application.model.Revision;
import com.gestion.application.model.RevisionType;
import com.gestion.application.repository.*;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class CreateRevisionImplTest {

  @Mock private RevisionRepository revisionRepository;
  @Mock private ContactRepository contactRepository;
  @Mock private PatientRepository patientRepository;
  @Mock private RevisionTypeRepository revisionTypeRepository;
  @Mock private ProtocolRepository protocolRepository;
  @Mock private RevisionMapper revisionMapper;

  @InjectMocks private CreateRevisionImpl service;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void execute_missingMandatoryFields_throwsRevisionInvalidDataException() {
    CreateRevisionRequest req = new CreateRevisionRequest();
    // No seteo ningún campo obligatorio

    RevisionInvalidDataException ex =
        assertThrows(RevisionInvalidDataException.class, () -> service.execute(req));
    assertTrue(ex.getMessage().contains("Faltan datos obligatorios"));
  }

  @Test
  void execute_contactNotFound_throwsContactNotFoundException() {
    CreateRevisionRequest req = new CreateRevisionRequest();
    req.setIdContact(1);
    req.setIdType(2);
    req.setObservations("Obs");
    req.setRevisionDate(LocalDate.now());

    when(contactRepository.findById(1)).thenReturn(Optional.empty());

    assertThrows(ContactNotFoundException.class, () -> service.execute(req));
  }

  @Test
  void execute_revisionTypeNotFound_throwsRevisionTypeNotFoundException() {
    CreateRevisionRequest req = new CreateRevisionRequest();
    req.setIdContact(1);
    req.setIdType(2);
    req.setObservations("Obs");
    req.setRevisionDate(LocalDate.now());

    when(contactRepository.findById(1)).thenReturn(Optional.of(new Contact()));
    when(revisionTypeRepository.findById(2)).thenReturn(Optional.empty());

    assertThrows(RevisionTypeNotFoundException.class, () -> service.execute(req));
  }

  @Test
  void execute_protocolNotFound_throwsProtocolNotFoundException() {
    CreateRevisionRequest req = new CreateRevisionRequest();
    req.setIdContact(1);
    req.setIdType(2);
    req.setIdProtocol(3);
    req.setObservations("Obs");
    req.setRevisionDate(LocalDate.now());

    when(contactRepository.findById(1)).thenReturn(Optional.of(new Contact()));
    when(revisionTypeRepository.findById(2)).thenReturn(Optional.of(new RevisionType()));
    when(protocolRepository.findById(3)).thenReturn(Optional.empty());

    assertThrows(ProtocolNotFoundException.class, () -> service.execute(req));
  }

  @Test
  void execute_patientNotFound_throwsPatientNotFoundException() {
    CreateRevisionRequest req = new CreateRevisionRequest();
    req.setIdContact(1);
    req.setIdType(2);
    req.setIdPatient(4);
    req.setObservations("Obs");
    req.setRevisionDate(LocalDate.now());

    when(contactRepository.findById(1)).thenReturn(Optional.of(new Contact()));
    when(revisionTypeRepository.findById(2)).thenReturn(Optional.of(new RevisionType()));
    when(protocolRepository.findById(null))
        .thenReturn(Optional.empty()); // no se llama en este caso
    when(patientRepository.findById(4)).thenReturn(Optional.empty());

    assertThrows(PatientNotFoundException.class, () -> service.execute(req));
  }

  @Test
  void execute_saveThrowsException_throwsRevisionCreationException() {
    CreateRevisionRequest req = new CreateRevisionRequest();
    req.setIdContact(1);
    req.setIdType(2);
    req.setObservations("Obs");
    req.setRevisionDate(LocalDate.now());

    Contact contact = new Contact();
    RevisionType type = new RevisionType();
    Revision revisionEntity = new Revision();

    when(contactRepository.findById(1)).thenReturn(Optional.of(contact));
    when(revisionTypeRepository.findById(2)).thenReturn(Optional.of(type));
    when(protocolRepository.findById(null)).thenReturn(Optional.empty());
    when(patientRepository.findById(null)).thenReturn(Optional.empty());
    when(revisionMapper.toEntity(req)).thenReturn(revisionEntity);
    when(revisionRepository.save(revisionEntity)).thenThrow(new RuntimeException("DB error"));

    assertThrows(RevisionCreationException.class, () -> service.execute(req));
  }
}
