package com.gestion.application.service.consultation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ConsultationRequest;
import com.gestion.application.dto.ConsultationResponse;
import com.gestion.application.model.Consultation;
import com.gestion.application.service.consultation.impl.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class ConsultationServiceTest {

  @Mock private CreateConsultationImpl createImpl;
  @Mock private GetAllConsultationsImpl allImpl;
  @Mock private GetVisibleConsultationsImpl visibleImpl;
  @Mock private DeleteConsultationImpl deleteImpl;
  @Mock private MakeConsultationVisibleImpl showImpl;
  @Mock private MakeConsultationInvisibleImpl hideImpl;
  @Mock private GetConsultationsByContactImpl byContactImpl;
  @Mock private GetConsultationsByTypeImpl byTypeImpl;
  @Mock private UpdateConsultationImpl updateImpl;
  @Mock private com.gestion.application.mapper.ConsultationMapper mapper;

  @InjectMocks private ConsultationService service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldCreateConsultation() {
    ConsultationRequest request = new ConsultationRequest();
    ConsultationResponse expected = new ConsultationResponse();

    when(createImpl.create(request)).thenReturn(expected);

    ConsultationResponse result = service.create(request);

    assertEquals(expected, result);
    verify(createImpl).create(request);
  }

  @Test
  void shouldGetAllConsultations() {
    List<ConsultationResponse> expected = List.of(new ConsultationResponse());
    when(allImpl.getAll()).thenReturn(expected);

    List<ConsultationResponse> result = service.getAll();

    assertEquals(expected, result);
    verify(allImpl).getAll();
  }

  @Test
  void shouldGetVisibleConsultations() {
    List<ConsultationResponse> expected = List.of(new ConsultationResponse());
    when(visibleImpl.getVisible()).thenReturn(expected);

    List<ConsultationResponse> result = service.getVisible();

    assertEquals(expected, result);
    verify(visibleImpl).getVisible();
  }

  @Test
  void shouldDeleteConsultation() {
    Integer id = 1;
    doNothing().when(deleteImpl).delete(id);

    service.delete(id);

    verify(deleteImpl).delete(id);
  }

  @Test
  void shouldMakeConsultationVisible() {
    Integer id = 1;
    Consultation consultation = new Consultation();
    ConsultationResponse response = new ConsultationResponse();

    when(showImpl.setVisible(id)).thenReturn(consultation);
    when(mapper.toResponse(consultation)).thenReturn(response);

    ConsultationResponse result = service.makeVisible(id);

    assertEquals(response, result);
    verify(showImpl).setVisible(id);
    verify(mapper).toResponse(consultation);
  }

  @Test
  void shouldMakeConsultationInvisible() {
    Integer id = 2;
    Consultation consultation = new Consultation();
    ConsultationResponse response = new ConsultationResponse();

    when(hideImpl.setInvisible(id)).thenReturn(consultation);
    when(mapper.toResponse(consultation)).thenReturn(response);

    ConsultationResponse result = service.makeInvisible(id);

    assertEquals(response, result);
    verify(hideImpl).setInvisible(id);
    verify(mapper).toResponse(consultation);
  }

  @Test
  void shouldGetConsultationsByContact() {
    Integer contactId = 3;
    List<ConsultationResponse> expected = List.of(new ConsultationResponse());

    when(byContactImpl.getByContact(contactId)).thenReturn(expected);

    List<ConsultationResponse> result = service.getByContact(contactId);

    assertEquals(expected, result);
    verify(byContactImpl).getByContact(contactId);
  }

  @Test
  void shouldGetConsultationsByType() {
    Integer typeId = 4;
    List<ConsultationResponse> expected = List.of(new ConsultationResponse());

    when(byTypeImpl.getByType(typeId)).thenReturn(expected);

    List<ConsultationResponse> result = service.getByType(typeId);

    assertEquals(expected, result);
    verify(byTypeImpl).getByType(typeId);
  }

  @Test
  void shouldUpdateConsultation() {
    Integer id = 5;
    ConsultationRequest req = new ConsultationRequest();
    ConsultationResponse expected = new ConsultationResponse();

    when(updateImpl.update(id, req)).thenReturn(expected);

    ConsultationResponse result = service.updateConsultation(id, req);

    assertEquals(expected, result);
    verify(updateImpl).update(id, req);
  }
}
