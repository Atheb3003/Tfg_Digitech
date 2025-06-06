package com.gestion.application.service.surgery.impl;

import com.gestion.application.dto.SurgeryReservationResponse;
import com.gestion.application.model.SurgeryReservation;
import com.gestion.application.repository.SurgeryReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetAllSurgeryReservationsImpl {

  private final SurgeryReservationRepository reservationRepository;

  public Page<SurgeryReservationResponse> getAllVisible(Pageable pageable) {
    return reservationRepository.findAllByIsVisibleTrue(pageable).map(this::mapToResponse);
  }

  private SurgeryReservationResponse mapToResponse(SurgeryReservation reservation) {
    SurgeryReservationResponse dto = new SurgeryReservationResponse();
    dto.setId(reservation.getIdSurgeryReservation());
    dto.setIdPatient(reservation.getPatient().getIdPatient());
    dto.setIdContact(reservation.getPatient().getContact().getIdContact());
    dto.setContactFullName(
        reservation.getPatient().getContact().getName()
            + " "
            + reservation.getPatient().getContact().getSurname());
    dto.setDescription(reservation.getDescription());
    dto.setFollicularUnits(reservation.getFollicularUnits());
    dto.setSurgicalTechnique(reservation.getSurgicalTechnique());
    dto.setEstimatedDate(reservation.getEstimatedDate());
    dto.setNational(reservation.getNational());
    dto.setDeposit(reservation.getDeposit());
    dto.setSurgeryPrice(reservation.getSurgeryPrice());
    dto.setIsVisible(reservation.getIsVisible());
    dto.setConfirmed(reservation.getConfirmed());

    return dto;
  }
}
