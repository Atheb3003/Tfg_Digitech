package com.gestion.application.service.revisiontype.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.RevisionTypeRequest;
import com.gestion.application.dto.RevisionTypeResponse;
import com.gestion.application.exception.RevisionTypeInvalidDataException;
import com.gestion.application.exception.RevisionTypeNotFoundException;
import com.gestion.application.mapper.RevisionTypeMapper;
import com.gestion.application.model.RevisionType;
import com.gestion.application.repository.RevisionTypeRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class UpdateRevisionTypeImplTest {

  @Mock private RevisionTypeRepository repo;
  @Mock private RevisionTypeMapper mapper;

  @InjectMocks private UpdateRevisionTypeImpl service;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void update_successfulUpdate_returnsResponse() {
    Integer id = 10;
    RevisionType existing = new RevisionType();
    RevisionTypeRequest req = new RevisionTypeRequest();
    req.setTypeName("UpdatedName");
    RevisionType saved = new RevisionType();
    RevisionTypeResponse response = new RevisionTypeResponse();

    when(repo.findById(id)).thenReturn(Optional.of(existing));
    when(repo.save(existing)).thenReturn(saved);
    when(mapper.toResponse(saved)).thenReturn(response);

    RevisionTypeResponse result = service.update(id, req);

    assertEquals(response, result);
    assertEquals("UpdatedName", existing.getTypeName());

    verify(repo).findById(id);
    verify(repo).save(existing);
    verify(mapper).toResponse(saved);
  }

  @Test
  void update_nonExistingId_throwsRevisionTypeNotFoundException() {
    Integer id = 10;
    RevisionTypeRequest req = new RevisionTypeRequest();
    req.setTypeName("UpdatedName");

    when(repo.findById(id)).thenReturn(Optional.empty());

    assertThrows(RevisionTypeNotFoundException.class, () -> service.update(id, req));
  }

  @Test
  void update_invalidTypeName_throwsRevisionTypeInvalidDataException() {
    Integer id = 10;
    RevisionType existing = new RevisionType();
    RevisionTypeRequest req1 = new RevisionTypeRequest();
    req1.setTypeName(null);
    RevisionTypeRequest req2 = new RevisionTypeRequest();
    req2.setTypeName("  ");

    when(repo.findById(id)).thenReturn(Optional.of(existing));

    assertThrows(RevisionTypeInvalidDataException.class, () -> service.update(id, req1));
    assertThrows(RevisionTypeInvalidDataException.class, () -> service.update(id, req2));
  }
}
