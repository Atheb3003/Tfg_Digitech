package com.gestion.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RevisionTypeResponse {
  @JsonProperty("id_revison_type")
  private Integer idRevisionType;

  @JsonProperty("type_name")
  private String typeName;
}
