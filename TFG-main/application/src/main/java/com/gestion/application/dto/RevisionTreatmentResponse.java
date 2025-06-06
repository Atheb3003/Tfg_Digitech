package com.gestion.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RevisionTreatmentResponse {
  private Integer id;

  @JsonProperty("id_revision")
  private Integer revisionId;

  @JsonProperty("id_product")
  private Integer productId;
}
