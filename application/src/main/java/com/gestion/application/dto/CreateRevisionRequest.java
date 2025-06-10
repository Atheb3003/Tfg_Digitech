package com.gestion.application.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/** DTO para capturar la petición de creación o actualización de Revision. */
@Getter
@Setter
public class CreateRevisionRequest {
  // ID del contacto al que pertenece esta revisión (obligatorio)
  private Integer idContact;
  // ID del tipo de revisión (obligatorio)
  private Integer idType;
  // ID del protocolo (opcional)
  private Integer idProtocol;
  // Observaciones de la revisión (obligatorio, no vacío)
  private String observations;
  // Fecha de la revisión (obligatorio)
  private LocalDate revisionDate;
  // ID numérico del paciente (opcional). Si se envía null, se desasocia.
  private Integer idPatient;
}
