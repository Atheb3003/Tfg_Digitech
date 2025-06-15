package com.gestion.application.service.revisiontype;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.RevisionTypeRequest;
import com.gestion.application.dto.RevisionTypeResponse;
import com.gestion.application.service.revisiontype.impl.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class RevisionTypeServiceTest {

  @Mock private CreateRevisionTypeImpl create;
  @Mock private GetAllRevisionTypesImpl all;
  @Mock private GetRevisionTypeByIdImpl getById;
  @Mock private UpdateRevisionTypeImpl update;
  @Mock private DeleteRevisionTypeImpl delete;

  @InjectMocks private RevisionTypeService service;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void create_delegatesToCreate() {
    RevisionTypeRequest req = new RevisionTypeRequest();
    RevisionTypeResponse resp = new RevisionTypeResponse();

    when(create.create(req)).thenReturn(resp);

    RevisionTypeResponse result = service.create(req);

    assertEquals(resp, result);
    verify(create).create(req);
  }

  @Test
  void getAll_delegatesToAll() {
    List<RevisionTypeResponse> list = List.of(new RevisionTypeResponse());

    when(all.getAll()).thenReturn(list);

    List<RevisionTypeResponse> result = service.getAll();

    assertEquals(list, result);
    verify(all).getAll();
  }

  @Test
  void getById_delegatesToGetById() {
    Integer id = 1;
    RevisionTypeResponse resp = new RevisionTypeResponse();

    when(getById.getById(id)).thenReturn(resp);

    RevisionTypeResponse result = service.getById(id);

    assertEquals(resp, result);
    verify(getById).getById(id);
  }

  @Test
  void update_delegatesToUpdate() {
    Integer id = 1;
    RevisionTypeRequest req = new RevisionTypeRequest();
    RevisionTypeResponse resp = new RevisionTypeResponse();

    when(update.update(id, req)).thenReturn(resp);

    RevisionTypeResponse result = service.update(id, req);

    assertEquals(resp, result);
    verify(update).update(id, req);
  }

  @Test
  void delete_delegatesToDelete() {
    Integer id = 1;
    doNothing().when(delete).delete(id);

    service.delete(id);

    verify(delete).delete(id);
  }
}
