package com.gestion.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class CreateSurgeryReservationRequest {
  private Integer idPatient;
  private String description;
  private Integer follicularUnits;
  private String surgicalTechnique;
  private LocalDate estimatedDate;
  private Boolean national;
  private BigDecimal deposit;
  private BigDecimal surgeryPrice;
  private Boolean isVisible;
}
