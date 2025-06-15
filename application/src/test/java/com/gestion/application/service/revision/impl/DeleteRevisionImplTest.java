package com.gestion.application.service.revision.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.exception.RevisionNotFoundException;
import com.gestion.application.model.Revision;
import com.gestion.application.repository.RevisionRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class DeleteRevisionImplTest {

  @Mock private RevisionRepository revisionRepository;

  @InjectMocks private DeleteRevisionImpl service;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void execute_existingId_deletesRevision() {
    Revision revision = new Revision();
    revision.setIdRevision(1);

    when(revisionRepository.findById(1)).thenReturn(Optional.of(revision));

    service.execute(1);

    verify(revisionRepository).findById(1);
    verify(revisionRepository).delete(revision);
  }

  @Test
  void execute_nonExistingId_throwsRevisionNotFoundException() {
    when(revisionRepository.findById(1)).thenReturn(Optional.empty());

    RevisionNotFoundException ex =
        assertThrows(RevisionNotFoundException.class, () -> service.execute(1));

    assertTrue(ex.getMessage().contains("1"));
    verify(revisionRepository).findById(1);
    verify(revisionRepository, never()).delete(any());
  }
}
