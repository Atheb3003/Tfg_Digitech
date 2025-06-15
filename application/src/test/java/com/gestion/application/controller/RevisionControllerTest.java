package com.gestion.application.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.*;
import com.gestion.application.service.revision.RevisionService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

class RevisionControllerTest {

  @InjectMocks private RevisionController revisionController;

  @Mock private RevisionService revisionService;

  private RevisionResponse dummyResponse;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    dummyResponse = new RevisionResponse();
    dummyResponse.setIdRevision(1);
    dummyResponse.setIdContact(1);
    dummyResponse.setIdType(2);
    dummyResponse.setObservations("Test obs");
    dummyResponse.setRevisionDate(LocalDate.now());
    dummyResponse.setIsVisible(true);
  }

  @Test
  void testCreateRevision() {
    CreateRevisionRequest request = new CreateRevisionRequest();
    when(revisionService.createRevision(request)).thenReturn(dummyResponse);

    ResponseEntity<ApiResponse<RevisionResponse>> response =
        revisionController.createRevision(request);

    assertEquals(201, response.getStatusCodeValue());
    assertEquals("created", response.getBody().getStatus());
    assertEquals(dummyResponse, response.getBody().getData());
  }

  @Test
  void testUpdateRevision() {
    CreateRevisionRequest request = new CreateRevisionRequest();
    when(revisionService.updateRevision(1, request)).thenReturn(dummyResponse);

    ResponseEntity<ApiResponse<RevisionResponse>> response =
        revisionController.updateRevision(1, request);

    assertEquals("updated", response.getBody().getStatus());
    assertEquals(dummyResponse, response.getBody().getData());
  }

  @Test
  void testDeleteRevision() {
    doNothing().when(revisionService).deleteRevision(1);

    ResponseEntity<SuccessfulDeleteResponse> response = revisionController.deleteRevision(1);

    assertEquals(200, response.getStatusCodeValue());
    assertTrue(response.getBody().getMessage().contains("eliminada correctamente"));
  }

  @Test
  void testGetRevisionById() {
    when(revisionService.getRevisionById(1)).thenReturn(dummyResponse);

    ResponseEntity<ApiResponse<RevisionResponse>> response = revisionController.getRevisionById(1);

    assertEquals("success", response.getBody().getStatus());
    assertEquals(dummyResponse, response.getBody().getData());
  }

  @Test
  void testGetAllRevisions() {
    when(revisionService.getAllRevisions()).thenReturn(List.of(dummyResponse));

    ResponseEntity<ApiResponse<List<RevisionResponse>>> response =
        revisionController.getAllRevisions();

    assertEquals("success", response.getBody().getStatus());
    assertEquals(1, response.getBody().getData().size());
  }

  @Test
  void testGetVisibleRevisions() {
    when(revisionService.getVisibleRevisions()).thenReturn(List.of(dummyResponse));

    ResponseEntity<ApiResponse<List<RevisionResponse>>> response =
        revisionController.getVisibleRevisions();

    assertEquals("success", response.getBody().getStatus());
    assertFalse(response.getBody().getData().isEmpty());
  }

  @Test
  void testGetRevisionsByContact() {
    when(revisionService.getRevisionsByContact(1)).thenReturn(List.of(dummyResponse));

    ResponseEntity<ApiResponse<List<RevisionResponse>>> response =
        revisionController.getRevisionsByContact(1);

    assertEquals("success", response.getBody().getStatus());
    assertEquals(1, response.getBody().getData().size());
  }

  @Test
  void testGetRevisionsByType() {
    when(revisionService.getRevisionsByType(2)).thenReturn(List.of(dummyResponse));

    ResponseEntity<ApiResponse<List<RevisionResponse>>> response =
        revisionController.getRevisionsByType(2);

    assertEquals("success", response.getBody().getStatus());
    assertEquals(1, response.getBody().getData().size());
  }

  @Test
  void testSetRevisionVisible() {
    when(revisionService.makeRevisionVisible(1)).thenReturn(dummyResponse);

    ResponseEntity<ApiResponse<RevisionResponse>> response =
        revisionController.setRevisionVisible(1);

    assertEquals("updated", response.getBody().getStatus());
  }

  @Test
  void testSetRevisionInvisible() {
    when(revisionService.makeRevisionInvisible(1)).thenReturn(dummyResponse);

    ResponseEntity<ApiResponse<RevisionResponse>> response =
        revisionController.setRevisionInvisible(1);

    assertEquals("updated", response.getBody().getStatus());
  }
}
