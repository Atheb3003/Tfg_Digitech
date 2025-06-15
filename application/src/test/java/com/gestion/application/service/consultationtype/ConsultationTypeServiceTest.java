package com.gestion.application.service.consultationtype;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ConsultationTypeRequest;
import com.gestion.application.dto.ConsultationTypeResponse;
import com.gestion.application.service.consultationtype.impl.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class ConsultationTypeServiceTest {

  @Mock private CreateConsultationTypeImpl createImpl;
  @Mock private GetAllConsultationTypesImpl allImpl;
  @Mock private GetConsultationTypeByIdImpl getByIdImpl;
  @Mock private UpdateConsultationTypeImpl updateImpl;
  @Mock private DeleteConsultationTypeImpl deleteImpl;

  @InjectMocks private ConsultationTypeService service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldCreateConsultationType() {
    ConsultationTypeRequest request = new ConsultationTypeRequest();
    ConsultationTypeResponse expected = new ConsultationTypeResponse();

    when(createImpl.create(request)).thenReturn(expected);

    ConsultationTypeResponse result = service.createConsultationType(request);

    assertEquals(expected, result);
    verify(createImpl).create(request);
  }

  @Test
  void shouldGetAllConsultationTypes() {
    List<ConsultationTypeResponse> expected = List.of(new ConsultationTypeResponse());

    when(allImpl.getAll()).thenReturn(expected);

    List<ConsultationTypeResponse> result = service.getAllConsultationTypes();

    assertEquals(expected, result);
    verify(allImpl).getAll();
  }

  @Test
  void shouldGetConsultationTypeById() {
    Integer id = 1;
    ConsultationTypeResponse expected = new ConsultationTypeResponse();

    when(getByIdImpl.getById(id)).thenReturn(expected);

    ConsultationTypeResponse result = service.getConsultationTypeById(id);

    assertEquals(expected, result);
    verify(getByIdImpl).getById(id);
  }

  @Test
  void shouldUpdateConsultationType() {
    Integer id = 2;
    ConsultationTypeRequest request = new ConsultationTypeRequest();
    ConsultationTypeResponse expected = new ConsultationTypeResponse();

    when(updateImpl.update(id, request)).thenReturn(expected);

    ConsultationTypeResponse result = service.updateConsultationType(id, request);

    assertEquals(expected, result);
    verify(updateImpl).update(id, request);
  }

  @Test
  void shouldDeleteConsultationType() {
    Integer id = 3;

    doNothing().when(deleteImpl).delete(id);

    service.deleteConsultationType(id);

    verify(deleteImpl).delete(id);
  }
}
