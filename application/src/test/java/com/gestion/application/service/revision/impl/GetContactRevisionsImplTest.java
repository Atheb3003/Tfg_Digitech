package com.gestion.application.service.revision.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.RevisionResponse;
import com.gestion.application.mapper.RevisionMapper;
import com.gestion.application.model.Revision;
import com.gestion.application.repository.RevisionRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class GetContactRevisionsImplTest {

  @Mock private RevisionRepository revisionRepository;
  @Mock private RevisionMapper revisionMapper;

  @InjectMocks private GetContactRevisionsImpl service;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void execute_returnsMappedRevisionsForContact() {
    Integer contactId = 42;

    Revision rev1 = new Revision();
    Revision rev2 = new Revision();

    RevisionResponse resp1 = new RevisionResponse();
    RevisionResponse resp2 = new RevisionResponse();

    when(revisionRepository.findByContact_IdContact(contactId))
        .thenReturn(Arrays.asList(rev1, rev2));
    when(revisionMapper.toDto(rev1)).thenReturn(resp1);
    when(revisionMapper.toDto(rev2)).thenReturn(resp2);

    List<RevisionResponse> result = service.execute(contactId);

    assertNotNull(result);
    assertEquals(2, result.size());
    assertTrue(result.contains(resp1));
    assertTrue(result.contains(resp2));

    verify(revisionRepository).findByContact_IdContact(contactId);
    verify(revisionMapper).toDto(rev1);
    verify(revisionMapper).toDto(rev2);
  }
}
