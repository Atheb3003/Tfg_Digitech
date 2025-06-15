package com.gestion.application.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProtocolTreatmentInfoDto {
  private Integer id;
  private BigDecimal price;
  private String productName;
  private Boolean isPaid;
}
