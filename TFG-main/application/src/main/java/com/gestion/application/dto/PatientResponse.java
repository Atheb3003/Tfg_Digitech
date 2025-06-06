package com.gestion.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.Data;

/** DTO de respuesta para paciente */
@Data
public class PatientResponse {
  @JsonProperty("id_patient")
  private Integer idPatient;

  @JsonProperty("id_contact")
  private Integer idContact;

  @JsonProperty("discharge_date")
  private LocalDate dischargeDate;

  @JsonProperty("is_visible")
  private Boolean isVisible;
}
