package com.gestion.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FullDetailSurgeryResponse {
    private Integer idSurgery;
    private Integer idReservation;
    private LocalDate date;
    private String observations;
    private Boolean isVisible;

    private Integer idPatient;
    private Integer idContact;
    private String  idContactString;
    private String  contactFullName;

    private Integer follicularUnits;
    private String  surgicalTechnique;
    private Boolean national;
    private BigDecimal deposit;
    private BigDecimal surgeryPrice;
    private BigDecimal remainingMoney;
}