package com.gestion.application.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class ProtocolDetailsDto {
  private Integer idProtocol;
  private String protocolDescription;
  private BigDecimal totalPrice;
  private String idContactString;
  private Boolean isFinished;
  private List<ProtocolTreatmentInfoDto> protocolTreatments;
}
