package com.gestion.application.service.surgery.impl;

import com.gestion.application.dto.SurgeryReservationResponse;
import com.gestion.application.dto.UpdateSurgeryReservationRequest;
import com.gestion.application.exception.SurgeryReservationNotFoundException;
import com.gestion.application.model.SurgeryReservation;
import com.gestion.application.repository.SurgeryReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateSurgeryReservationImpl {

  private final SurgeryReservationRepository reservationRepository;

  public SurgeryReservationResponse update(Integer id, UpdateSurgeryReservationRequest request) {
    SurgeryReservation reservation =
        reservationRepository
            .findById(id)
            .orElseThrow(() -> new SurgeryReservationNotFoundException(id));

    reservation.setDescription(request.getDescription());
    reservation.setFollicularUnits(request.getFollicularUnits());
    reservation.setSurgicalTechnique(request.getSurgicalTechnique());
    reservation.setEstimatedDate(request.getEstimatedDate());
    reservation.setNational(request.getNational());
    reservation.setDeposit(request.getDeposit());
    reservation.setSurgeryPrice(request.getSurgeryPrice());
    reservation.setIsVisible(request.getIsVisible());

    reservationRepository.save(reservation);

    SurgeryReservationResponse response = new SurgeryReservationResponse();
    response.setId(reservation.getIdSurgeryReservation());
    response.setDescription(reservation.getDescription());
    response.setFollicularUnits(reservation.getFollicularUnits());
    response.setSurgicalTechnique(reservation.getSurgicalTechnique());
    response.setEstimatedDate(reservation.getEstimatedDate());
    response.setNational(reservation.getNational());
    response.setDeposit(reservation.getDeposit());
    response.setSurgeryPrice(reservation.getSurgeryPrice());
    response.setIsVisible(reservation.getIsVisible());

    if (reservation.getPatient() != null) {
      response.setIdPatient(reservation.getPatient().getIdPatient());

      if (reservation.getPatient().getContact() != null) {
        var contact = reservation.getPatient().getContact();
        response.setContactFullName(contact.getName() + " " + contact.getSurname());
      }
    }

    return response;
  }
}
