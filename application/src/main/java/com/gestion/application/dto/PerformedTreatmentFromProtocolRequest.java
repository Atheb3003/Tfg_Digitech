package com.gestion.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.Data;

@Data
public class PerformedTreatmentFromProtocolRequest {

  @JsonProperty("contactId")
  private Integer contactId;

  @JsonProperty("protocolTreatmentId")
  private Integer protocolTreatmentId;

  @JsonProperty("performedDate")
  private LocalDate performedDate;

  @JsonProperty("finalPrice")
  private Double finalPrice;

  @JsonProperty("notes")
  private String notes; // opcional

  @JsonProperty("revisionId")
  private Integer revisionId; // nuevo campo opcional
}
