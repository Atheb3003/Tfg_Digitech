package com.gestion.application.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProtocolSearchTreatmentDto {
  private Integer treatmentId;
  private BigDecimal treatmentPrice;
  private String productName;
  private Boolean paid;
  private Boolean complete;
}
