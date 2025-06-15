package com.gestion.application.service.revision.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.CreateRevisionRequest;
import com.gestion.application.exception.*;
import com.gestion.application.mapper.RevisionMapper;
import com.gestion.application.model.*;
import com.gestion.application.repository.*;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class UpdateRevisionImplTest {

  @Mock private RevisionRepository revisionRepository;
  @Mock private ContactRepository contactRepository;
  @Mock private PatientRepository patientRepository;
  @Mock private RevisionTypeRepository revisionTypeRepository;
  @Mock private ProtocolRepository protocolRepository;
  @Mock private RevisionMapper revisionMapper;

  @InjectMocks private UpdateRevisionImpl service;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  private CreateRevisionRequest validRequest() {
    CreateRevisionRequest req = new CreateRevisionRequest();
    req.setIdContact(1);
    req.setIdType(2);
    req.setIdProtocol(3);
    req.setIdPatient(4);
    req.setObservations("Observations");
    req.setRevisionDate(LocalDate.now());
    return req;
  }

  @Test
  void execute_revisionNotFound_throwsRevisionNotFoundException() {
    Integer id = 10;
    CreateRevisionRequest req = validRequest();

    when(revisionRepository.findById(id)).thenReturn(Optional.empty());

    assertThrows(RevisionNotFoundException.class, () -> service.execute(id, req));
  }

  @Test
  void execute_missingMandatoryFields_throwsRevisionInvalidDataException() {
    Integer id = 10;
    CreateRevisionRequest req = new CreateRevisionRequest(); // empty

    Revision existing = new Revision();
    when(revisionRepository.findById(id)).thenReturn(Optional.of(existing));

    RevisionInvalidDataException ex =
        assertThrows(RevisionInvalidDataException.class, () -> service.execute(id, req));

    assertTrue(ex.getMessage().contains("Faltan datos obligatorios"));
  }

  @Test
  void execute_contactNotFound_throwsContactNotFoundException() {
    Integer id = 10;
    CreateRevisionRequest req = validRequest();

    Revision existing = new Revision();

    when(revisionRepository.findById(id)).thenReturn(Optional.of(existing));
    when(contactRepository.findById(1)).thenReturn(Optional.empty());

    assertThrows(ContactNotFoundException.class, () -> service.execute(id, req));
  }

  @Test
  void execute_revisionTypeNotFound_throwsRevisionTypeNotFoundException() {
    Integer id = 10;
    CreateRevisionRequest req = validRequest();

    Revision existing = new Revision();

    when(revisionRepository.findById(id)).thenReturn(Optional.of(existing));
    when(contactRepository.findById(1)).thenReturn(Optional.of(new Contact()));
    when(revisionTypeRepository.findById(2)).thenReturn(Optional.empty());

    assertThrows(RevisionTypeNotFoundException.class, () -> service.execute(id, req));
  }

  @Test
  void execute_protocolNotFound_throwsProtocolNotFoundException() {
    Integer id = 10;
    CreateRevisionRequest req = validRequest();

    Revision existing = new Revision();

    when(revisionRepository.findById(id)).thenReturn(Optional.of(existing));
    when(contactRepository.findById(1)).thenReturn(Optional.of(new Contact()));
    when(revisionTypeRepository.findById(2)).thenReturn(Optional.of(new RevisionType()));
    when(protocolRepository.findById(3)).thenReturn(Optional.empty());

    assertThrows(ProtocolNotFoundException.class, () -> service.execute(id, req));
  }

  @Test
  void execute_patientNotFound_throwsPatientNotFoundException() {
    Integer id = 10;
    CreateRevisionRequest req = validRequest();

    Revision existing = new Revision();

    when(revisionRepository.findById(id)).thenReturn(Optional.of(existing));
    when(contactRepository.findById(1)).thenReturn(Optional.of(new Contact()));
    when(revisionTypeRepository.findById(2)).thenReturn(Optional.of(new RevisionType()));
    when(protocolRepository.findById(3)).thenReturn(Optional.of(new Protocol()));
    when(patientRepository.findById(4)).thenReturn(Optional.empty());

    assertThrows(PatientNotFoundException.class, () -> service.execute(id, req));
  }
}
