package com.gestion.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class SurgeryReservationResponse {

  // Para que coincida con el JSON esperado
  private Integer idSurgeryReservation;
  private Integer idPatient;
  private Integer idContact;
  private String idContactString;
  private String contactFullName;

  private String description;
  private Integer follicularUnits;
  private String surgicalTechnique;
  private LocalDate estimatedDate;
  private Boolean national;

  private BigDecimal deposit;
  private BigDecimal surgeryPrice;
  private Boolean isVisible;
  private Boolean confirmed;

  private BigDecimal remainingMoney;
  private Boolean isPaid;
}
