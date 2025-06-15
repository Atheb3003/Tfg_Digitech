// com.gestion.application.dto.TotalIncomeResponse.java

package com.gestion.application.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TotalIncomeResponse {
  private Double totalIncome;
  private LocalDate startDate;
  private LocalDate endDate;

  public TotalIncomeResponse(Double totalIncome) {
    this.totalIncome = totalIncome;
  }
}
