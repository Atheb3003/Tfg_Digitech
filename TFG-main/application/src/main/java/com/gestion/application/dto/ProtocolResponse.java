package com.gestion.application.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class ProtocolResponse {
  private Integer idProtocol;
  private String description;
  private BigDecimal price;
  private Boolean isFinished;
  private List<ProtocolTreatmentResponse> treatments;
}
