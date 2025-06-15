package com.gestion.application.service.revisiontype.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.RevisionTypeResponse;
import com.gestion.application.mapper.RevisionTypeMapper;
import com.gestion.application.model.RevisionType;
import com.gestion.application.repository.RevisionTypeRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class GetAllRevisionTypesImplTest {

  @Mock private RevisionTypeRepository repo;
  @Mock private RevisionTypeMapper mapper;

  @InjectMocks private GetAllRevisionTypesImpl service;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getAll_returnsMappedResponses() {
    RevisionType rt1 = new RevisionType();
    RevisionType rt2 = new RevisionType();

    RevisionTypeResponse resp1 = new RevisionTypeResponse();
    RevisionTypeResponse resp2 = new RevisionTypeResponse();

    when(repo.findAll()).thenReturn(Arrays.asList(rt1, rt2));
    when(mapper.toResponse(rt1)).thenReturn(resp1);
    when(mapper.toResponse(rt2)).thenReturn(resp2);

    List<RevisionTypeResponse> result = service.getAll();

    assertNotNull(result);
    assertEquals(2, result.size());
    assertTrue(result.contains(resp1));
    assertTrue(result.contains(resp2));

    verify(repo).findAll();
  }
}
