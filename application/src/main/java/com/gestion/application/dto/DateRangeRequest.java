package com.gestion.application.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class DateRangeRequest {
  private LocalDate startDate;
  private LocalDate endDate;
}
