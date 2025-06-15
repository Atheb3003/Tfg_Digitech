package com.gestion.application.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.*;
import com.gestion.application.exception.IdArgumentTypeException;
import com.gestion.application.service.consultation.ConsultationService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

class ConsultationControllerTest {

  @Mock private ConsultationService service;

  @InjectMocks private ConsultationController controller;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCreate() {
    ConsultationRequest request = new ConsultationRequest();
    ConsultationResponse response = new ConsultationResponse();
    when(service.create(request)).thenReturn(response);

    ResponseEntity<ApiResponse<ConsultationResponse>> result = controller.create(request);
    assertEquals(201, result.getStatusCodeValue());
    assertEquals(response, result.getBody().getData());
  }

  @Test
  void testGetAll() {
    when(service.getAll()).thenReturn(List.of(new ConsultationResponse()));

    ResponseEntity<ApiResponse<List<ConsultationResponse>>> result = controller.getAll();
    assertEquals(200, result.getStatusCodeValue());
    assertEquals(1, result.getBody().getData().size());
  }

  @Test
  void testGetVisible() {
    when(service.getVisible()).thenReturn(List.of(new ConsultationResponse()));

    ResponseEntity<ApiResponse<List<ConsultationResponse>>> result = controller.getVisible();
    assertEquals(200, result.getStatusCodeValue());
  }

  @Test
  void testGetByContact_validId() {
    when(service.getByContact(10)).thenReturn(List.of(new ConsultationResponse()));
    var result = controller.getByContact("10");

    assertEquals(200, result.getStatusCodeValue());
    assertEquals(1, result.getBody().getData().size());
  }

  @Test
  void testGetByContact_invalidId_throwsException() {
    assertThrows(IdArgumentTypeException.class, () -> controller.getByContact("abc"));
  }

  @Test
  void testDelete_validId() {
    doNothing().when(service).delete(5);

    ResponseEntity<SuccessfulDeleteResponse> response = controller.delete("5");

    assertEquals(200, response.getStatusCodeValue());
    assertTrue(response.getBody().getMessage().contains("eliminada"));
  }

  @Test
  void testDelete_invalidId() {
    assertThrows(IdArgumentTypeException.class, () -> controller.delete("xyz"));
  }

  @Test
  void testMakeVisible_validId() {
    ConsultationResponse mock = new ConsultationResponse();
    when(service.makeVisible(2)).thenReturn(mock);

    var response = controller.makeVisible("2");

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(mock, response.getBody().getData());
  }

  @Test
  void testMakeInvisible_validId() {
    ConsultationResponse mock = new ConsultationResponse();
    when(service.makeInvisible(3)).thenReturn(mock);

    var response = controller.makeInvisible("3");

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(mock, response.getBody().getData());
  }

  @Test
  void testUpdate_validId() {
    ConsultationRequest req = new ConsultationRequest();
    ConsultationResponse res = new ConsultationResponse();
    when(service.updateConsultation(7, req)).thenReturn(res);

    var response = controller.update("7", req);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(res, response.getBody().getData());
  }

  @Test
  void testUpdate_invalidId() {
    assertThrows(
        IdArgumentTypeException.class, () -> controller.update("xx", new ConsultationRequest()));
  }

  @Test
  void testGetByType_validId() {
    when(service.getByType(1)).thenReturn(List.of(new ConsultationResponse()));
    var response = controller.getByType("1");

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(1, response.getBody().getData().size());
  }

  @Test
  void testGetByType_invalidId() {
    assertThrows(IdArgumentTypeException.class, () -> controller.getByType("abc"));
  }
}
