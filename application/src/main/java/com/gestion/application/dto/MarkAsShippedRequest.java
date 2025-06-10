package com.gestion.application.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class MarkAsShippedRequest {
  private boolean enviado;
  private LocalDate fechaRealEnvio;

  // Getters y Setters
}
