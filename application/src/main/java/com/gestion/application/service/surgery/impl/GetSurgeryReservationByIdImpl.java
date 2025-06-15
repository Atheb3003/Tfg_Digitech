package com.gestion.application.service.surgery.impl;

import com.gestion.application.dto.SurgeryReservationResponse;
import com.gestion.application.exception.SurgeryReservationNotFoundException;
import com.gestion.application.model.SurgeryReservation;
import com.gestion.application.repository.SurgeryReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetSurgeryReservationByIdImpl {

  private final SurgeryReservationRepository reservationRepository;

  public SurgeryReservationResponse getById(Integer id) {
    SurgeryReservation reservation =
        reservationRepository
            .findById(id)
            .orElseThrow(() -> new SurgeryReservationNotFoundException(id));

    SurgeryReservationResponse dto = new SurgeryReservationResponse();
    dto.setIdSurgeryReservation(reservation.getIdSurgeryReservation());
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
    // Nuevos campos
    dto.setRemainingMoney(reservation.getRemainingMoney());
    dto.setIsPaid(reservation.getIsPaid());

    return dto;
  }
}
