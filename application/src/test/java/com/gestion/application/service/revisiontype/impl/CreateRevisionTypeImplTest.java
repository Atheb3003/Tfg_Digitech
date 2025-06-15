package com.gestion.application.service.revisiontype.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.RevisionTypeRequest;
import com.gestion.application.dto.RevisionTypeResponse;
import com.gestion.application.exception.RevisionTypeInvalidDataException;
import com.gestion.application.mapper.RevisionTypeMapper;
import com.gestion.application.model.RevisionType;
import com.gestion.application.repository.RevisionTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class CreateRevisionTypeImplTest {

  @Mock private RevisionTypeRepository repo;
  @Mock private RevisionTypeMapper mapper;

  @InjectMocks private CreateRevisionTypeImpl service;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void create_validRequest_savesAndReturnsResponse() {
    RevisionTypeRequest req = new RevisionTypeRequest();
    req.setTypeName("TestType");

    RevisionType entity = new RevisionType();
    RevisionType savedEntity = new RevisionType();
    RevisionTypeResponse expectedResponse = new RevisionTypeResponse();

    when(mapper.toEntity(req)).thenReturn(entity);
    when(repo.save(entity)).thenReturn(savedEntity);
    when(mapper.toResponse(savedEntity)).thenReturn(expectedResponse);

    RevisionTypeResponse result = service.create(req);

    assertEquals(expectedResponse, result);

    verify(mapper).toEntity(req);
    verify(repo).save(entity);
    verify(mapper).toResponse(savedEntity);
  }

  @Test
  void create_nullOrBlankTypeName_throwsException() {
    RevisionTypeRequest req1 = new RevisionTypeRequest();
    req1.setTypeName(null);

    RevisionTypeRequest req2 = new RevisionTypeRequest();
    req2.setTypeName("   ");

    assertThrows(RevisionTypeInvalidDataException.class, () -> service.create(req1));
    assertThrows(RevisionTypeInvalidDataException.class, () -> service.create(req2));
  }
}
