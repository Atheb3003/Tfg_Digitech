package com.gestion.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class SurgeryReservationResponse {
  private Integer id;
  private String description;
  private Integer follicularUnits;
  private String surgicalTechnique;
  private LocalDate estimatedDate;
  private Boolean national;
  private BigDecimal deposit;
  private BigDecimal surgeryPrice;
  private Boolean isVisible;

  private Integer idPatient;
  private Integer idContact;
  private String contactFullName;
  private Boolean confirmed;
}
