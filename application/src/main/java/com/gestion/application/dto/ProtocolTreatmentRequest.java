package com.gestion.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProtocolTreatmentRequest {

  @JsonProperty("id_protocol")
  private Integer protocolId;

  @JsonProperty("id_product")
  private Integer productId;

  @JsonProperty("is_finished")
  private Boolean isFinished;

  @JsonProperty("is_paid")
  private Boolean isPaid;
}
