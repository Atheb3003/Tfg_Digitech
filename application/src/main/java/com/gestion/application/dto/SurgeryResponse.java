package com.gestion.application.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SurgeryResponse {
    private Integer idSurgery;
    private Integer idSurgeryReservation;
    private LocalDate date;
    private String observations;
    private Boolean isVisible;
}
