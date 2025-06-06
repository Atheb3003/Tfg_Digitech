package com.gestion.application.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProtocolListResponse {
  private Integer idProtocol;
  private String description;
  private BigDecimal price;
  private Boolean isFinished;

  private String contactName;
  private String contactTelephone;
  private String contactNif;
}
