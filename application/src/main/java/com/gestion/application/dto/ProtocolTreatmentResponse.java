package com.gestion.application.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProtocolTreatmentResponse {
  private Integer id;
  private Integer productId;
  private String productName;
  private BigDecimal productPrice;
  private Boolean isFinished;
}
