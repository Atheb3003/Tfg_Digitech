package com.gestion.application.service.revisiontreatment.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.RevisionTreatmentResponse;
import com.gestion.application.mapper.RevisionTreatmentMapper;
import com.gestion.application.model.RevisionTreatment;
import com.gestion.application.repository.RevisionTreatmentRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class GetAllRevisionTreatmentsImplTest {

  @Mock private RevisionTreatmentRepository repo;
  @Mock private RevisionTreatmentMapper mapper;

  @InjectMocks private GetAllRevisionTreatmentsImpl service;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getAll_returnsMappedResponses() {
    RevisionTreatment rt1 = new RevisionTreatment();
    RevisionTreatment rt2 = new RevisionTreatment();

    RevisionTreatmentResponse resp1 = new RevisionTreatmentResponse();
    RevisionTreatmentResponse resp2 = new RevisionTreatmentResponse();

    when(repo.findAll()).thenReturn(Arrays.asList(rt1, rt2));
    when(mapper.toResponse(rt1)).thenReturn(resp1);
    when(mapper.toResponse(rt2)).thenReturn(resp2);

    List<RevisionTreatmentResponse> result = service.getAll();

    assertNotNull(result);
    assertEquals(2, result.size());
    assertTrue(result.contains(resp1));
    assertTrue(result.contains(resp2));

    verify(repo).findAll();
  }
}
