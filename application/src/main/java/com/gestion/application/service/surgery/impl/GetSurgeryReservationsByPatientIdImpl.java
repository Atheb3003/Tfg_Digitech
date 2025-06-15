package com.gestion.application.service.surgery.impl;

import com.gestion.application.dto.SurgeryReservationResponse;
import com.gestion.application.exception.PatientNotFoundException;
import com.gestion.application.model.SurgeryReservation;
import com.gestion.application.repository.PatientRepository;
import com.gestion.application.repository.SurgeryReservationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetSurgeryReservationsByPatientIdImpl {

  private final SurgeryReservationRepository reservationRepository;
  private final PatientRepository patientRepository;

  public List<SurgeryReservationResponse> getByPatientId(Integer idPatient) {
    if (!patientRepository.existsById(idPatient)) {
      throw new PatientNotFoundException(idPatient);
    }

    return reservationRepository.findByPatient_IdPatient(idPatient).stream()
        .map(this::mapToResponse)
        .toList();
  }

  private SurgeryReservationResponse mapToResponse(SurgeryReservation reservation) {
    SurgeryReservationResponse dto = new SurgeryReservationResponse();
    dto.setIdSurgeryReservation(reservation.getIdSurgeryReservation());
    dto.setDescription(reservation.getDescription());
    dto.setFollicularUnits(reservation.getFollicularUnits());
    dto.setSurgicalTechnique(reservation.getSurgicalTechnique());
    dto.setEstimatedDate(reservation.getEstimatedDate());
    dto.setNational(reservation.getNational());
    dto.setDeposit(reservation.getDeposit());
    dto.setSurgeryPrice(reservation.getSurgeryPrice());
    dto.setIsVisible(reservation.getIsVisible());

    dto.setIdPatient(reservation.getPatient().getIdPatient());
    dto.setIdContact(reservation.getPatient().getContact().getIdContact());
    dto.setContactFullName(
        reservation.getPatient().getContact().getName()
            + " "
            + reservation.getPatient().getContact().getSurname());

    dto.setConfirmed(reservation.getConfirmed());

    // Nuevos campos
    dto.setRemainingMoney(reservation.getRemainingMoney());
    dto.setIsPaid(reservation.getIsPaid());

    return dto;
  }
}
