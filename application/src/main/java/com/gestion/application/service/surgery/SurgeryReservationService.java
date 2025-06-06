package com.gestion.application.service.surgery;

import com.gestion.application.dto.CreateSurgeryRequest;
import com.gestion.application.dto.CreateSurgeryReservationRequest;
import com.gestion.application.dto.SurgeryReservationResponse;
import com.gestion.application.dto.UpdateSurgeryReservationRequest;
import com.gestion.application.model.Surgery;
import com.gestion.application.model.SurgeryReservation;
import com.gestion.application.service.surgery.impl.*;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SurgeryReservationService {

  private final CreateSurgeryReservationImpl createSurgeryReservation;
  private final GetAllSurgeryReservationsImpl getAllReservations;
  private final GetSurgeryReservationByIdImpl getById;
  private final GetSurgeryReservationsByPatientIdImpl getByPatientId;
  private final CreateSurgeryImpl createSurgery;
  private final CreateStandaloneSurgeryImpl createStandaloneSurgery;
  private final UpdateSurgeryReservationImpl updateSurgeryReservation;
  private final GetAllHiddenReservationsImpl getAllHiddenReservations;
  private final HideSurgeryReservationImpl hideImpl;

  public SurgeryReservation createReservation(CreateSurgeryReservationRequest request) {
    return createSurgeryReservation.create(request);
  }

  public Page<SurgeryReservationResponse> getAllReservations(Pageable pageable) {
    return getAllReservations.getAllVisible(pageable);
  }

  public Page<SurgeryReservationResponse> getAllHidden(Pageable pageable) {
    return getAllHiddenReservations.getAllHidden(pageable);
  }

  public SurgeryReservationResponse getReservationById(Integer id) {
    return getById.getById(id);
  }

  public List<SurgeryReservationResponse> getReservationsByPatientId(Integer id) {
    return getByPatientId.getByPatientId(id);
  }

  public Surgery createSurgery(CreateSurgeryRequest request) {
    return createSurgery.createSurgery(
        request.getReservationId(), request.getDate(), request.getObservations());
  }

  public Surgery createStandaloneSurgery(CreateSurgeryRequest request) {
    return createStandaloneSurgery.create(request);
  }

  public SurgeryReservationResponse updateReservation(
      Integer id, UpdateSurgeryReservationRequest request) {
    return updateSurgeryReservation.update(id, request);
  }

  public void hideReservation(Integer id) {
    hideImpl.hide(id);
  }
}
