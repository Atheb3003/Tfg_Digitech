package com.gestion.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HideReservationResponse {
  private Integer idSurgeryReservation;
  private Boolean isVisible;
}
