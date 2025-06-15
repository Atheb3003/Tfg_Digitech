package com.gestion.application.service.revision;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.CreateRevisionRequest;
import com.gestion.application.dto.RevisionResponse;
import com.gestion.application.service.revision.impl.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class RevisionServiceTest {

  @Mock private CreateRevisionImpl createRevisionImpl;
  @Mock private UpdateRevisionImpl updateRevisionImpl;
  @Mock private DeleteRevisionImpl deleteRevisionImpl;
  @Mock private GetAllRevisionsImpl getAllRevisionsImpl;
  @Mock private GetVisibleRevisionsImpl getVisibleRevisionsImpl;
  @Mock private GetContactRevisionsImpl getContactRevisionsImpl;
  @Mock private GetRevisionByIdImpl getRevisionByIdImpl;
  @Mock private GetRevisionsByTypeImpl getRevisionsByTypeImpl;
  @Mock private SetRevisionVisibleImpl setRevisionVisibleImpl;
  @Mock private SetRevisionInvisibleImpl setRevisionInvisibleImpl;

  @InjectMocks private RevisionService service;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void createRevision_delegatesToCreateRevisionImpl() {
    CreateRevisionRequest req = new CreateRevisionRequest();
    RevisionResponse resp = new RevisionResponse();

    when(createRevisionImpl.execute(req)).thenReturn(resp);

    RevisionResponse result = service.createRevision(req);

    assertEquals(resp, result);
    verify(createRevisionImpl).execute(req);
  }

  @Test
  void updateRevision_delegatesToUpdateRevisionImpl() {
    Integer id = 1;
    CreateRevisionRequest req = new CreateRevisionRequest();
    RevisionResponse resp = new RevisionResponse();

    when(updateRevisionImpl.execute(id, req)).thenReturn(resp);

    RevisionResponse result = service.updateRevision(id, req);

    assertEquals(resp, result);
    verify(updateRevisionImpl).execute(id, req);
  }

  @Test
  void deleteRevision_delegatesToDeleteRevisionImpl() {
    Integer id = 1;

    doNothing().when(deleteRevisionImpl).execute(id);

    service.deleteRevision(id);

    verify(deleteRevisionImpl).execute(id);
  }

  @Test
  void getAllRevisions_delegatesToGetAllRevisionsImpl() {
    List<RevisionResponse> list = List.of(new RevisionResponse());

    when(getAllRevisionsImpl.execute()).thenReturn(list);

    List<RevisionResponse> result = service.getAllRevisions();

    assertEquals(list, result);
    verify(getAllRevisionsImpl).execute();
  }

  @Test
  void getVisibleRevisions_delegatesToGetVisibleRevisionsImpl() {
    List<RevisionResponse> list = List.of(new RevisionResponse());

    when(getVisibleRevisionsImpl.execute()).thenReturn(list);

    List<RevisionResponse> result = service.getVisibleRevisions();

    assertEquals(list, result);
    verify(getVisibleRevisionsImpl).execute();
  }

  @Test
  void getRevisionsByContact_delegatesToGetContactRevisionsImpl() {
    Integer contactId = 5;
    List<RevisionResponse> list = List.of(new RevisionResponse());

    when(getContactRevisionsImpl.execute(contactId)).thenReturn(list);

    List<RevisionResponse> result = service.getRevisionsByContact(contactId);

    assertEquals(list, result);
    verify(getContactRevisionsImpl).execute(contactId);
  }

  @Test
  void getRevisionById_delegatesToGetRevisionByIdImpl() {
    Integer id = 10;
    RevisionResponse resp = new RevisionResponse();

    when(getRevisionByIdImpl.execute(id)).thenReturn(resp);

    RevisionResponse result = service.getRevisionById(id);

    assertEquals(resp, result);
    verify(getRevisionByIdImpl).execute(id);
  }

  @Test
  void getRevisionsByType_delegatesToGetRevisionsByTypeImpl() {
    Integer typeId = 3;
    List<RevisionResponse> list = List.of(new RevisionResponse());

    when(getRevisionsByTypeImpl.execute(typeId)).thenReturn(list);

    List<RevisionResponse> result = service.getRevisionsByType(typeId);

    assertEquals(list, result);
    verify(getRevisionsByTypeImpl).execute(typeId);
  }

  @Test
  void makeRevisionVisible_delegatesToSetRevisionVisibleImpl() {
    Integer id = 7;
    RevisionResponse resp = new RevisionResponse();

    when(setRevisionVisibleImpl.execute(id)).thenReturn(resp);

    RevisionResponse result = service.makeRevisionVisible(id);

    assertEquals(resp, result);
    verify(setRevisionVisibleImpl).execute(id);
  }

  @Test
  void makeRevisionInvisible_delegatesToSetRevisionInvisibleImpl() {
    Integer id = 8;
    RevisionResponse resp = new RevisionResponse();

    when(setRevisionInvisibleImpl.execute(id)).thenReturn(resp);

    RevisionResponse result = service.makeRevisionInvisible(id);

    assertEquals(resp, result);
    verify(setRevisionInvisibleImpl).execute(id);
  }
}
