package com.gestion.application.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.*;
import com.gestion.application.exception.IdArgumentTypeException;
import com.gestion.application.service.revisiontype.RevisionTypeService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

class RevisionTypeControllerTest {

  @InjectMocks private RevisionTypeController controller;

  @Mock private RevisionTypeService service;

  private RevisionTypeResponse dummyResponse;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    dummyResponse = new RevisionTypeResponse();
    dummyResponse.setIdRevisionType(1);
    dummyResponse.setTypeName("General");
  }

  @Test
  void testCreate() {
    RevisionTypeRequest request = new RevisionTypeRequest();
    when(service.create(request)).thenReturn(dummyResponse);

    ResponseEntity<ApiResponse<RevisionTypeResponse>> response = controller.create(request);

    assertEquals(201, response.getStatusCodeValue());
    assertEquals("created", response.getBody().getStatus());
    assertEquals(dummyResponse, response.getBody().getData());
  }

  @Test
  void testGetAll() {
    when(service.getAll()).thenReturn(List.of(dummyResponse));

    ResponseEntity<ApiResponse<List<RevisionTypeResponse>>> response = controller.getAll();

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("success", response.getBody().getStatus());
    assertFalse(response.getBody().getData().isEmpty());
  }

  @Test
  void testGetById_validId() {
    when(service.getById(1)).thenReturn(dummyResponse);

    ResponseEntity<ApiResponse<RevisionTypeResponse>> response = controller.getById("1");

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("success", response.getBody().getStatus());
    assertEquals(dummyResponse, response.getBody().getData());
  }

  @Test
  void testGetById_invalidId() {
    assertThrows(IdArgumentTypeException.class, () -> controller.getById("abc"));
  }

  @Test
  void testUpdate_validId() {
    RevisionTypeRequest request = new RevisionTypeRequest();
    when(service.update(1, request)).thenReturn(dummyResponse);

    ResponseEntity<ApiResponse<RevisionTypeResponse>> response = controller.update("1", request);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("updated", response.getBody().getStatus());
    assertEquals(dummyResponse, response.getBody().getData());
  }

  @Test
  void testUpdate_invalidId() {
    RevisionTypeRequest request = new RevisionTypeRequest();
    assertThrows(IdArgumentTypeException.class, () -> controller.update("xyz", request));
  }

  @Test
  void testDelete_validId() {
    doNothing().when(service).delete(1);

    ResponseEntity<SuccessfulDeleteResponse> response = controller.delete("1");

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("deleted", response.getBody().getStatus());
    assertTrue(response.getBody().getMessage().contains("eliminado correctamente"));
  }

  @Test
  void testDelete_invalidId() {
    assertThrows(IdArgumentTypeException.class, () -> controller.delete("bad_id"));
  }
}
