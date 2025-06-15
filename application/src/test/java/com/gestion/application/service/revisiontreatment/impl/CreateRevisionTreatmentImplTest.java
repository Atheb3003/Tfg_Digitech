package com.gestion.application.service.revisiontreatment.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.RevisionTreatmentRequest;
import com.gestion.application.exception.*;
import com.gestion.application.mapper.RevisionTreatmentMapper;
import com.gestion.application.repository.ProductRepository;
import com.gestion.application.repository.RevisionRepository;
import com.gestion.application.repository.RevisionTreatmentRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class CreateRevisionTreatmentImplTest {

  @Mock private RevisionTreatmentRepository repo;
  @Mock private RevisionRepository revisionRepo;
  @Mock private ProductRepository productRepo;
  @Mock private RevisionTreatmentMapper mapper;

  @InjectMocks private CreateRevisionTreatmentImpl service;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  private RevisionTreatmentRequest validRequest() {
    RevisionTreatmentRequest req = new RevisionTreatmentRequest();
    req.setRevisionId(1);
    req.setProductId(2);
    return req;
  }

  @Test
  void create_nullFields_throwsInvalidDataException() {
    RevisionTreatmentRequest req = new RevisionTreatmentRequest();
    req.setRevisionId(null);
    req.setProductId(2);

    assertThrows(RevisionTreatmentInvalidDataException.class, () -> service.create(req));

    req.setRevisionId(1);
    req.setProductId(null);

    assertThrows(RevisionTreatmentInvalidDataException.class, () -> service.create(req));
  }

  @Test
  void create_revisionNotFound_throwsRevisionNotFoundException() {
    RevisionTreatmentRequest req = validRequest();
    when(revisionRepo.findById(1)).thenReturn(Optional.empty());

    assertThrows(RevisionNotFoundException.class, () -> service.create(req));
  }
}
