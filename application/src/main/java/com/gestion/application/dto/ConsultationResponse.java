package com.gestion.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ConsultationResponse {
  @JsonProperty("id_consultation")
  private Integer idConsultation;

  @JsonProperty("id_contact")
  private Integer contactId;

  @JsonProperty("id_patient")
  private Integer patientId;

  @JsonProperty("id_type")
  private Integer typeId;

  @JsonProperty("follicular_units")
  private Integer follicularUnits;

  @JsonProperty("insertion_zones")
  private String insertionZones;

  private String observations;

  @JsonProperty("treatment_done")
  private Boolean treatmentDone;

  @JsonProperty("consultation_date")
  private LocalDateTime consultationDate;

  @JsonProperty("is_visible")
  private Boolean isVisible;
}
