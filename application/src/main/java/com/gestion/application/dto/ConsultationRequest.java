package com.gestion.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ConsultationRequest {
  @JsonProperty("id_contact")
  private Integer contactId;

  @JsonProperty("id_type")
  private Integer typeId;

  @JsonProperty("follicular_units")
  private Integer follicularUnits;

  @JsonProperty("insertion_zones")
  private String insertionZones;

  private String observations;

  @JsonProperty("treatment_done")
  private Boolean treatmentDone;

  @JsonProperty("surgery_reserved")
  private Boolean surgeryReserved;

  @JsonProperty("consultation_date")
  private LocalDate consultationDate;
}
