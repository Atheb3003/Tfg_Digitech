package com.gestion.application.service.surgery;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.*;
import com.gestion.application.model.Surgery;
import com.gestion.application.model.SurgeryReservation;
import com.gestion.application.service.surgery.impl.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

class SurgeryReservationServiceTest {

  @Mock private CreateSurgeryReservationImpl createSurgeryReservation;

  @Mock private GetAllSurgeryReservationsImpl getAllReservations;

  @Mock private GetSurgeryReservationByIdImpl getById;

  @Mock private GetSurgeryReservationsByPatientIdImpl getByPatientId;

  @Mock private CreateSurgeryImpl createSurgery;

  @Mock private CreateStandaloneSurgeryImpl createStandaloneSurgery;

  @Mock private UpdateSurgeryReservationImpl updateSurgeryReservation;

  @Mock private GetAllHiddenReservationsImpl getAllHiddenReservations;

  @Mock private HideSurgeryReservationImpl hideImpl;

  @Mock private GetVisibleSurgeryReservationsImpl visibleReservationsImpl;

  @Mock private AddPaymentToReservationImpl addPaymentImpl;

  @InjectMocks private SurgeryReservationService service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void createReservation_shouldDelegateToCreateSurgeryReservation() {
    CreateSurgeryReservationRequest request = new CreateSurgeryReservationRequest();
    SurgeryReservation expected = new SurgeryReservation();

    when(createSurgeryReservation.create(request)).thenReturn(expected);

    SurgeryReservation actual = service.createReservation(request);

    assertSame(expected, actual);
    verify(createSurgeryReservation).create(request);
  }

  @Test
  void getAllReservations_shouldDelegateToGetAllReservations() {
    Pageable pageable = PageRequest.of(0, 10);
    Page<SurgeryReservationResponse> page = Page.empty(pageable);

    when(getAllReservations.getAllVisible(pageable)).thenReturn(page);

    Page<SurgeryReservationResponse> actual = service.getAllReservations(pageable);

    assertSame(page, actual);
    verify(getAllReservations).getAllVisible(pageable);
  }

  @Test
  void getVisibleReservations_shouldDelegateToGetAllReservations() {
    Pageable pageable = PageRequest.of(0, 10);
    Page<SurgeryReservationResponse> page = Page.empty(pageable);

    when(getAllReservations.getAllVisible(pageable)).thenReturn(page);

    Page<SurgeryReservationResponse> actual = service.getVisibleReservations(pageable);

    assertSame(page, actual);
    verify(getAllReservations).getAllVisible(pageable);
  }

  @Test
  void getAllHidden_shouldDelegateToGetAllHiddenReservations() {
    Pageable pageable = PageRequest.of(0, 10);
    Page<SurgeryReservationResponse> page = Page.empty(pageable);

    when(getAllHiddenReservations.getAllHidden(pageable)).thenReturn(page);

    Page<SurgeryReservationResponse> actual = service.getAllHidden(pageable);

    assertSame(page, actual);
    verify(getAllHiddenReservations).getAllHidden(pageable);
  }

  @Test
  void getReservationById_shouldDelegateToGetById() {
    Integer id = 1;
    SurgeryReservationResponse response = new SurgeryReservationResponse();

    when(getById.getById(id)).thenReturn(response);

    SurgeryReservationResponse actual = service.getReservationById(id);

    assertSame(response, actual);
    verify(getById).getById(id);
  }

  @Test
  void getReservationsByPatientId_shouldDelegateToGetByPatientId() {
    Integer patientId = 1;
    List<SurgeryReservationResponse> list = List.of(new SurgeryReservationResponse());

    when(getByPatientId.getByPatientId(patientId)).thenReturn(list);

    List<SurgeryReservationResponse> actual = service.getReservationsByPatientId(patientId);

    assertSame(list, actual);
    verify(getByPatientId).getByPatientId(patientId);
  }

  @Test
  void createSurgery_shouldDelegateToCreateSurgery() {
    CreateSurgeryRequest request = new CreateSurgeryRequest();
    request.setReservationId(1);
    request.setDate(LocalDate.now());
    request.setObservations("obs");

    Surgery surgery = new Surgery();

    when(createSurgery.createSurgery(
            request.getReservationId(), request.getDate(), request.getObservations()))
        .thenReturn(surgery);

    Surgery actual = service.createSurgery(request);

    assertSame(surgery, actual);
    verify(createSurgery)
        .createSurgery(request.getReservationId(), request.getDate(), request.getObservations());
  }

  @Test
  void createStandaloneSurgery_shouldDelegate() {
    CreateSurgeryRequest request = new CreateSurgeryRequest();
    Surgery surgery = new Surgery();

    when(createStandaloneSurgery.create(request)).thenReturn(surgery);

    Surgery actual = service.createStandaloneSurgery(request);

    assertSame(surgery, actual);
    verify(createStandaloneSurgery).create(request);
  }

  @Test
  void updateReservation_shouldDelegate() {
    Integer id = 1;
    UpdateSurgeryReservationRequest request = new UpdateSurgeryReservationRequest();
    SurgeryReservationResponse response = new SurgeryReservationResponse();

    when(updateSurgeryReservation.update(id, request)).thenReturn(response);

    SurgeryReservationResponse actual = service.updateReservation(id, request);

    assertSame(response, actual);
    verify(updateSurgeryReservation).update(id, request);
  }

  @Test
  void hideReservation_shouldDelegate() {
    Integer id = 1;

    doNothing().when(hideImpl).hide(id);

    service.hideReservation(id);

    verify(hideImpl).hide(id);
  }

  @Test
  void addPayment_shouldDelegate() {
    Integer id = 1;
    BigDecimal amount = BigDecimal.valueOf(123.45);
    SurgeryReservation reservation = new SurgeryReservation();

    when(addPaymentImpl.addPayment(id, amount)).thenReturn(reservation);

    SurgeryReservation actual = service.addPayment(id, amount);

    assertSame(reservation, actual);
    verify(addPaymentImpl).addPayment(id, amount);
  }

  @Test
  void getOnlyVisibleReservations_shouldDelegate() {
    Pageable pageable = PageRequest.of(0, 10);
    Page<SurgeryReservationResponse> page = Page.empty(pageable);

    when(visibleReservationsImpl.getVisible(pageable)).thenReturn(page);

    Page<SurgeryReservationResponse> actual = service.getOnlyVisibleReservations(pageable);

    assertSame(page, actual);
    verify(visibleReservationsImpl).getVisible(pageable);
  }
}
