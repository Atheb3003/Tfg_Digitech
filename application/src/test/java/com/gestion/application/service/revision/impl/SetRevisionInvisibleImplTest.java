package com.gestion.application.service.revision.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.RevisionResponse;
import com.gestion.application.exception.RevisionNotFoundException;
import com.gestion.application.mapper.RevisionMapper;
import com.gestion.application.model.Revision;
import com.gestion.application.repository.RevisionRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class SetRevisionInvisibleImplTest {

  @Mock private RevisionRepository revisionRepository;

  @Mock private RevisionMapper revisionMapper;

  @InjectMocks private SetRevisionInvisibleImpl service;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void execute_existingRevision_setsInvisibleAndReturnsDto() {
    Integer id = 7;
    Revision revision = new Revision();
    revision.setIsVisible(true);

    Revision savedRevision = new Revision();
    savedRevision.setIsVisible(false);

    RevisionResponse expectedResponse = new RevisionResponse();

    when(revisionRepository.findById(id)).thenReturn(Optional.of(revision));
    when(revisionRepository.save(revision)).thenReturn(savedRevision);
    when(revisionMapper.toDto(savedRevision)).thenReturn(expectedResponse);

    RevisionResponse result = service.execute(id);

    assertNotNull(result);
    assertEquals(expectedResponse, result);
    assertFalse(revision.getIsVisible());

    verify(revisionRepository).findById(id);
    verify(revisionRepository).save(revision);
    verify(revisionMapper).toDto(savedRevision);
  }

  @Test
  void execute_nonExistingRevision_throwsRevisionNotFoundException() {
    Integer id = 7;
    when(revisionRepository.findById(id)).thenReturn(Optional.empty());

    RevisionNotFoundException ex =
        assertThrows(RevisionNotFoundException.class, () -> service.execute(id));

    assertTrue(ex.getMessage().contains(id.toString()));

    verify(revisionRepository).findById(id);
    verify(revisionRepository, never()).save(any());
    verify(revisionMapper, never()).toDto(any());
  }
}
