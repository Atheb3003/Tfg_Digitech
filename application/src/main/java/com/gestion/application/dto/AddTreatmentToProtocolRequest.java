package com.gestion.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class AddTreatmentToProtocolRequest {

  @JsonProperty("id_protocol")
  private Integer protocolId;

  @JsonProperty("id_product")
  private Integer productId;

  private String detail;

  private BigDecimal price; // ← nuevo campo obligatorio
}
