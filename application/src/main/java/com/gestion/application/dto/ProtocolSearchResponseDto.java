package com.gestion.application.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class ProtocolSearchResponseDto {
  private Integer protocolId;
  private String protocolDescription;
  private BigDecimal protocolTotalPrice;
  private String contactIdString;
  private Boolean finished;
  private List<ProtocolSearchTreatmentDto> treatments;
}
