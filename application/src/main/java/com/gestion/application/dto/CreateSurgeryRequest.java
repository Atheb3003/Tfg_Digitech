package com.gestion.application.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class CreateSurgeryRequest {
  private Integer reservationId;
  private LocalDate date;
  private String observations;
}
