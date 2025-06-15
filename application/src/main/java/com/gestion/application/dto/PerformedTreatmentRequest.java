package com.gestion.application.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class PerformedTreatmentRequest {
  private Integer contactId;
  private Integer productId; // Opcional
  private Integer revisionId; // Nuevo campo
  private LocalDate performedDate;
}
