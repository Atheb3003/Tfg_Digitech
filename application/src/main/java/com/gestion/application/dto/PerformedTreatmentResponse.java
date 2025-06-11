package com.gestion.application.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformedTreatmentResponse {

  private Integer id;
  private LocalDate performedDate;
  private Double finalPrice;
  private String notes;
  private String productName;
  private Boolean isFromProtocol;
}
