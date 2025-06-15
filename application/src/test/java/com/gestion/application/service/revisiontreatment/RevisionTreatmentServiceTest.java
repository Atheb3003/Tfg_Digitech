package com.gestion.application.service.revisiontreatment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.RevisionTreatmentRequest;
import com.gestion.application.dto.RevisionTreatmentResponse;
import com.gestion.application.service.revisiontreatment.impl.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class RevisionTreatmentServiceTest {

  @Mock private CreateRevisionTreatmentImpl createImpl;
  @Mock private GetAllRevisionTreatmentsImpl allImpl;
  @Mock private GetRevisionTreatmentsByRevisionImpl byRevImpl;
  @Mock private DeleteRevisionTreatmentImpl deleteImpl;

  @InjectMocks private RevisionTreatmentService service;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void create_delegatesToCreateImpl() {
    RevisionTreatmentRequest req = new RevisionTreatmentRequest();
    RevisionTreatmentResponse resp = new RevisionTreatmentResponse();

    when(createImpl.create(req)).thenReturn(resp);

    RevisionTreatmentResponse result = service.create(req);

    assertEquals(resp, result);
    verify(createImpl).create(req);
  }

  @Test
  void getAll_delegatesToAllImpl() {
    List<RevisionTreatmentResponse> list = List.of(new RevisionTreatmentResponse());

    when(allImpl.getAll()).thenReturn(list);

    List<RevisionTreatmentResponse> result = service.getAll();

    assertEquals(list, result);
    verify(allImpl).getAll();
  }

  @Test
  void getByRevision_delegatesToByRevImpl() {
    Integer revId = 10;
    List<RevisionTreatmentResponse> list = List.of(new RevisionTreatmentResponse());

    when(byRevImpl.getByRevision(revId)).thenReturn(list);

    List<RevisionTreatmentResponse> result = service.getByRevision(revId);

    assertEquals(list, result);
    verify(byRevImpl).getByRevision(revId);
  }

  @Test
  void delete_delegatesToDeleteImpl() {
    Integer id = 5;

    doNothing().when(deleteImpl).delete(id);

    service.delete(id);

    verify(deleteImpl).delete(id);
  }
}
