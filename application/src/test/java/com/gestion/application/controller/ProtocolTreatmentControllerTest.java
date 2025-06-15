package com.gestion.application.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.AddTreatmentToProtocolRequest;
import com.gestion.application.dto.SuccessfulMarkAsPaidResponse;
import com.gestion.application.service.protocoltreatment.ProtocolTreatmentService;
import com.gestion.application.service.protocoltreatment.impl.AddTreatmentToProtocolService;
import com.gestion.application.service.protocoltreatment.impl.MarkProtocolTreatmentAsFinishedImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

class ProtocolTreatmentControllerTest {

  @Mock private ProtocolTreatmentService protocolTreatmentService;

  @Mock private AddTreatmentToProtocolService addTreatmentToProtocolService;

  @Mock private MarkProtocolTreatmentAsFinishedImpl markProtocolTreatmentAsFinished;

  @InjectMocks private ProtocolTreatmentController controller;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testMarkAsPaid() {
    SuccessfulMarkAsPaidResponse response =
        new SuccessfulMarkAsPaidResponse("success", "Tratamiento marcado como pagado.");
    when(protocolTreatmentService.markAsPaid(1)).thenReturn(response);

    ResponseEntity<SuccessfulMarkAsPaidResponse> result = controller.markAsPaid(1);

    assertEquals(200, result.getStatusCodeValue());
    assertEquals("success", result.getBody().getStatus());
  }

  @Test
  void testAddTreatment() {
    AddTreatmentToProtocolRequest request = new AddTreatmentToProtocolRequest();
    doNothing().when(addTreatmentToProtocolService).addTreatment(request);

    ResponseEntity<String> result = controller.addTreatment(request);

    assertEquals(200, result.getStatusCodeValue());
    assertEquals("Tratamiento añadido al protocolo y precio actualizado.", result.getBody());
  }

  @Test
  void testMarkAsFinished() {
    SuccessfulMarkAsPaidResponse response =
        new SuccessfulMarkAsPaidResponse("success", "Tratamiento marcado como finalizado.");
    when(markProtocolTreatmentAsFinished.markAsFinished(1)).thenReturn(response);

    ResponseEntity<SuccessfulMarkAsPaidResponse> result = controller.markAsFinished(1);

    assertEquals(200, result.getStatusCodeValue());
    assertEquals("Tratamiento marcado como finalizado.", result.getBody().getMessage());
  }
}
