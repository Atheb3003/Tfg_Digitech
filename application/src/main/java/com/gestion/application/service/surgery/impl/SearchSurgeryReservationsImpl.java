package com.gestion.application.service.surgery.impl;

import com.gestion.application.dto.SurgeryReservationResponse;
import com.gestion.application.model.SurgeryReservation;
import com.gestion.application.repository.SurgeryReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchSurgeryReservationsImpl {

    private final SurgeryReservationRepository repo;

    public Page<SurgeryReservationResponse> searchVisibleReservations(
            String search,
            Pageable pageable
    ) {
        return repo.searchVisibleReservations(search, pageable)
                .map(this::toDto);
    }

    private SurgeryReservationResponse toDto(SurgeryReservation r) {
        SurgeryReservationResponse dto = new SurgeryReservationResponse();
        dto.setIdSurgeryReservation(r.getIdSurgeryReservation());
        dto.setIdPatient(r.getPatient().getIdPatient());
        dto.setIdContact(r.getPatient().getContact().getIdContact());
        dto.setIdContactString(r.getPatient().getContact().getIdContactString());
        dto.setContactFullName(
                r.getPatient().getContact().getName() + " " +
                        r.getPatient().getContact().getSurname()
        );
        dto.setDescription(r.getDescription());
        dto.setFollicularUnits(r.getFollicularUnits());
        dto.setSurgicalTechnique(r.getSurgicalTechnique());
        dto.setEstimatedDate(r.getEstimatedDate());
        dto.setNational(r.getNational());
        dto.setDeposit(r.getDeposit());
        dto.setRemainingMoney(r.getRemainingMoney());
        dto.setSurgeryPrice(r.getSurgeryPrice());
        dto.setIsVisible(r.getIsVisible());
        dto.setConfirmed(r.getConfirmed());
        dto.setIsPaid(r.getIsPaid());
        return dto;
    }
}