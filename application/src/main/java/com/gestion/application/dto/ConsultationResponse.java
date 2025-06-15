package com.gestion.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.Data;

/** DTO de respuesta para la entidad Consultation */
@Data
public class ConsultationResponse {
  @JsonProperty("id_consultation")
  private Integer idConsultation;

  @JsonProperty("id_contact")
  private Integer contactId;

  @JsonProperty("IDType")
  private Integer idType;

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

  @JsonProperty("is_visible")
  private Boolean isVisible;

  @JsonProperty("nombre_contacto")
  private String nombreContacto;

  @JsonProperty("type")
  private String type;
}
