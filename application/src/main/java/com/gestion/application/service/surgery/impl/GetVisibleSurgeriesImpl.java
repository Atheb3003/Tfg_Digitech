package com.gestion.application.service.surgery.impl;

import com.gestion.application.dto.FullDetailSurgeryResponse;
import com.gestion.application.model.Contact;
import com.gestion.application.model.Surgery;
import com.gestion.application.model.SurgeryReservation;
import com.gestion.application.repository.SurgeryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetVisibleSurgeriesImpl {

  private final SurgeryRepository surgeryRepository;

  public Page<FullDetailSurgeryResponse> getVisibleSurgeries(Pageable pageable) {
    return surgeryRepository.findAllByIsVisibleTrue(pageable).map(this::toFullDetail);
  }

  private FullDetailSurgeryResponse toFullDetail(Surgery s) {
    FullDetailSurgeryResponse dto = new FullDetailSurgeryResponse();
    dto.setIdSurgery(s.getIdSurgery());
    dto.setDate(s.getDate());
    dto.setObservations(s.getObservations());
    dto.setIsVisible(s.getIsVisible());

    SurgeryReservation r = s.getSurgeryReservation();
    if (r != null) {
      dto.setIdReservation(r.getIdSurgeryReservation());
      dto.setFollicularUnits(r.getFollicularUnits());
      dto.setSurgicalTechnique(r.getSurgicalTechnique());
      dto.setNational(r.getNational());
      dto.setDeposit(r.getDeposit());
      dto.setSurgeryPrice(r.getSurgeryPrice());
      dto.setRemainingMoney(r.getRemainingMoney());

      if (r.getPatient() != null) {
        dto.setIdPatient(r.getPatient().getIdPatient());
        Contact c = r.getPatient().getContact();
        if (c != null) {
          dto.setIdContact(c.getIdContact());
          dto.setIdContactString(c.getIdContactString());
          dto.setContactFullName(c.getName() + " " + c.getSurname());
        }
      }
    }

    return dto;
  }
}
