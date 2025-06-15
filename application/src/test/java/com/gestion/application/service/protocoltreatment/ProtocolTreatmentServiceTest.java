package com.gestion.application.service.protocoltreatment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.SuccessfulMarkAsPaidResponse;
import com.gestion.application.service.protocoltreatment.impl.MarkProtocolTreatmentAsPaidImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class ProtocolTreatmentServiceTest {

  @Mock private MarkProtocolTreatmentAsPaidImpl markAsPaidImpl;

  @InjectMocks private ProtocolTreatmentService service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldDelegateMarkAsPaid() {
    Integer treatmentId = 1;
    SuccessfulMarkAsPaidResponse response = new SuccessfulMarkAsPaidResponse("success", "ok");

    when(markAsPaidImpl.markAsPaid(treatmentId)).thenReturn(response);

    SuccessfulMarkAsPaidResponse result = service.markAsPaid(treatmentId);

    assertEquals(response, result);
    verify(markAsPaidImpl).markAsPaid(treatmentId);
  }
}
