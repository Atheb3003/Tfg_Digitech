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

class GetRevisionByIdImplTest {

  @Mock private RevisionRepository revisionRepository;
  @Mock private RevisionMapper revisionMapper;

  @InjectMocks private GetRevisionByIdImpl service;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void execute_existingId_returnsMappedRevision() {
    Integer id = 10;
    Revision revision = new Revision();
    RevisionResponse response = new RevisionResponse();

    when(revisionRepository.findById(id)).thenReturn(Optional.of(revision));
    when(revisionMapper.toDto(revision)).thenReturn(response);

    RevisionResponse result = service.execute(id);

    assertNotNull(result);
    assertEquals(response, result);

    verify(revisionRepository).findById(id);
    verify(revisionMapper).toDto(revision);
  }

  @Test
  void execute_nonExistingId_throwsRevisionNotFoundException() {
    Integer id = 10;
    when(revisionRepository.findById(id)).thenReturn(Optional.empty());

    RevisionNotFoundException ex =
        assertThrows(RevisionNotFoundException.class, () -> service.execute(id));

    assertTrue(ex.getMessage().contains(id.toString()));
    verify(revisionRepository).findById(id);
    verify(revisionMapper, never()).toDto(any());
  }
}
