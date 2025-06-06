package com.gestion.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ConsultationTypeRequest {
  @JsonProperty("type_name")
  private String typeName;
}
