package com.gestion.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ConsultationTypeResponse {
  @JsonProperty("id_type")
  private Integer idType;

  @JsonProperty("type_name")
  private String typeName;
}
