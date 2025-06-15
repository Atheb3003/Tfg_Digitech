package com.gestion.application.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.*;
import com.gestion.application.exception.IdArgumentTypeException;
import com.gestion.application.service.revisiontreatment.RevisionTreatmentService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

class RevisionTreatmentControllerTest {

  @InjectMocks private RevisionTreatmentController controller;

  @Mock private RevisionTreatmentService service;

  private RevisionTreatmentResponse dummyResponse;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    dummyResponse = new RevisionTreatmentResponse();
    dummyResponse.setId(1);
    dummyResponse.setRevisionId(10);
    dummyResponse.setProductId(20);
  }

  @Test
  void testCreate() {
    RevisionTreatmentRequest request = new RevisionTreatmentRequest();
    when(service.create(request)).thenReturn(dummyResponse);

    ResponseEntity<ApiResponse<RevisionTreatmentResponse>> response = controller.create(request);

    assertEquals(201, response.getStatusCodeValue());
    assertEquals("created", response.getBody().getStatus());
    assertEquals(dummyResponse, response.getBody().getData());
  }

  @Test
  void testGetAll() {
    when(service.getAll()).thenReturn(List.of(dummyResponse));

    ResponseEntity<ApiResponse<List<RevisionTreatmentResponse>>> response = controller.getAll();

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("success", response.getBody().getStatus());
    assertEquals(1, response.getBody().getData().size());
  }

  @Test
  void testGetByRevision_validId() {
    when(service.getByRevision(10)).thenReturn(List.of(dummyResponse));

    ResponseEntity<ApiResponse<List<RevisionTreatmentResponse>>> response =
        controller.getByRevision("10");

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("success", response.getBody().getStatus());
    assertFalse(response.getBody().getData().isEmpty());
  }

  @Test
  void testGetByRevision_invalidId() {
    assertThrows(IdArgumentTypeException.class, () -> controller.getByRevision("abc"));
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
    assertThrows(IdArgumentTypeException.class, () -> controller.delete("xyz"));
  }
}
