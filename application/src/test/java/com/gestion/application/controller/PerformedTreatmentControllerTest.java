package com.gestion.application.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.*;
import com.gestion.application.service.performedTreatment.PerformedTreatmentFromProtocolService;
import com.gestion.application.service.performedTreatment.PerformedTreatmentService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

class PerformedTreatmentControllerTest {

  @Mock private PerformedTreatmentService performedTreatmentService;

  @Mock private PerformedTreatmentFromProtocolService fromProtocolService;

  @InjectMocks private PerformedTreatmentController controller;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCreatePerformedTreatment() {
    PerformedTreatmentRequest request = new PerformedTreatmentRequest();
    request.setContactId(1);
    request.setProductId(10);
    request.setRevisionId(5);
    request.setPerformedDate(LocalDate.now());

    doNothing().when(performedTreatmentService).savePerformedTreatment(request);

    ResponseEntity<Void> response = controller.create(request);

    assertEquals(200, response.getStatusCodeValue());
    verify(performedTreatmentService).savePerformedTreatment(request);
  }

  @Test
  void testAddFromProtocol() {
    PerformedTreatmentFromProtocolRequest request = new PerformedTreatmentFromProtocolRequest();
    request.setContactId(1);
    request.setProtocolTreatmentId(2);
    request.setPerformedDate(LocalDate.now());
    request.setFinalPrice(150.0);
    request.setNotes("nota");
    request.setRevisionId(3);

    doNothing().when(fromProtocolService).createFromProtocol(request);

    ResponseEntity<String> response = controller.addFromProtocol(request);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("Tratamiento de protocolo registrado correctamente.", response.getBody());
    verify(fromProtocolService).createFromProtocol(request);
  }

  @Test
  void testGetByContact() {
    PerformedTreatmentResponse t1 =
        new PerformedTreatmentResponse(1, LocalDate.now(), 100.0, "Nota", "Producto X", false, 3);
    PerformedTreatmentResponse t2 =
        new PerformedTreatmentResponse(2, LocalDate.now(), 120.0, "Obs", "Producto Y", true, 4);

    when(performedTreatmentService.getTreatmentsByContactId(1)).thenReturn(List.of(t1, t2));

    ResponseEntity<List<PerformedTreatmentResponse>> response = controller.getByContact(1);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(2, response.getBody().size());
    assertEquals("Producto X", response.getBody().get(0).getProductName());
  }
}
