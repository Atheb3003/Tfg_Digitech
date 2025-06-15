package com.gestion.application.service.revisiontype.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.RevisionTypeResponse;
import com.gestion.application.exception.RevisionTypeNotFoundException;
import com.gestion.application.mapper.RevisionTypeMapper;
import com.gestion.application.model.RevisionType;
import com.gestion.application.repository.RevisionTypeRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class GetRevisionTypeByIdImplTest {

  @Mock private RevisionTypeRepository repo;
  @Mock private RevisionTypeMapper mapper;

  @InjectMocks private GetRevisionTypeByIdImpl service;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getById_existingId_returnsMappedResponse() {
    Integer id = 7;
    RevisionType entity = new RevisionType();
    RevisionTypeResponse response = new RevisionTypeResponse();

    when(repo.findById(id)).thenReturn(Optional.of(entity));
    when(mapper.toResponse(entity)).thenReturn(response);

    RevisionTypeResponse result = service.getById(id);

    assertEquals(response, result);
    verify(repo).findById(id);
    verify(mapper).toResponse(entity);
  }

  @Test
  void getById_nonExistingId_throwsException() {
    Integer id = 7;

    when(repo.findById(id)).thenReturn(Optional.empty());

    RevisionTypeNotFoundException ex =
        assertThrows(RevisionTypeNotFoundException.class, () -> service.getById(id));

    assertTrue(ex.getMessage().contains(id.toString()));
    verify(repo).findById(id);
    verify(mapper, never()).toResponse(any());
  }
}
