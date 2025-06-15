package com.gestion.application.service.revisiontreatment.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.exception.RevisionNotFoundException;
import com.gestion.application.mapper.RevisionTreatmentMapper;
import com.gestion.application.repository.RevisionRepository;
import com.gestion.application.repository.RevisionTreatmentRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class GetRevisionTreatmentsByRevisionImplTest {

  @Mock private RevisionTreatmentRepository repo;
  @Mock private RevisionRepository revisionRepo;
  @Mock private RevisionTreatmentMapper mapper;

  @InjectMocks private GetRevisionTreatmentsByRevisionImpl service;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getByRevision_revisionNotFound_throwsException() {
    Integer revId = 1;
    when(revisionRepo.findById(revId)).thenReturn(Optional.empty());

    RevisionNotFoundException ex =
        assertThrows(RevisionNotFoundException.class, () -> service.getByRevision(revId));

    assertTrue(ex.getMessage().contains(revId.toString()));
    verify(revisionRepo).findById(revId);
    verify(repo, never()).findByRevision_IdRevision(any());
    verify(mapper, never()).toResponse(any());
  }
}
