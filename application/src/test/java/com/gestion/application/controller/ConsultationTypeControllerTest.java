package com.gestion.application.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.*;
import com.gestion.application.exception.IdArgumentTypeException;
import com.gestion.application.service.consultationtype.ConsultationTypeService;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

class ConsultationTypeControllerTest {

  @Mock private ConsultationTypeService service;

  @InjectMocks private ConsultationTypeController controller;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCreate() {
    ConsultationTypeRequest req = new ConsultationTypeRequest();
    ConsultationTypeResponse res = new ConsultationTypeResponse();
    when(service.createConsultationType(req)).thenReturn(res);

    ResponseEntity<ApiResponse<ConsultationTypeResponse>> response = controller.create(req);

    assertEquals(201, response.getStatusCodeValue());
    assertEquals("created", response.getBody().getStatus());
    assertEquals(res, response.getBody().getData());
  }

  @Test
  void testGetAll() {
    List<ConsultationTypeResponse> list = List.of(new ConsultationTypeResponse());
    when(service.getAllConsultationTypes()).thenReturn(list);

    ResponseEntity<Map<String, Object>> response = controller.getAll();

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("success", response.getBody().get("status"));
    assertEquals(list, response.getBody().get("data"));
  }

  @Test
  void testGetById_valid() {
    ConsultationTypeResponse res = new ConsultationTypeResponse();
    when(service.getConsultationTypeById(1)).thenReturn(res);

    ResponseEntity<Map<String, Object>> response = controller.getById("1");

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("success", response.getBody().get("status"));
    assertEquals(res, response.getBody().get("data"));
  }

  @Test
  void testGetById_invalid() {
    assertThrows(IdArgumentTypeException.class, () -> controller.getById("abc"));
  }

  @Test
  void testUpdate_valid() {
    ConsultationTypeRequest req = new ConsultationTypeRequest();
    ConsultationTypeResponse res = new ConsultationTypeResponse();
    when(service.updateConsultationType(1, req)).thenReturn(res);

    ResponseEntity<ApiResponse<ConsultationTypeResponse>> response = controller.update("1", req);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("updated", response.getBody().getStatus());
    assertEquals(res, response.getBody().getData());
  }

  @Test
  void testUpdate_invalid() {
    assertThrows(
        IdArgumentTypeException.class,
        () -> controller.update("xyz", new ConsultationTypeRequest()));
  }

  @Test
  void testDelete_valid() {
    doNothing().when(service).deleteConsultationType(5);

    ResponseEntity<SuccessfulDeleteResponse> response = controller.delete("5");

    assertEquals(200, response.getStatusCodeValue());
    assertTrue(response.getBody().getMessage().contains("eliminado correctamente"));
  }

  @Test
  void testDelete_invalid() {
    assertThrows(IdArgumentTypeException.class, () -> controller.delete("no-num"));
  }
}
