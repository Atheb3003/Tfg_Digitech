// com.gestion.application.dto.IncomeByPaymentMethodResponse.java
package com.gestion.application.dto;

import java.time.LocalDate;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IncomeByPaymentMethodResponse {
  private Map<String, String> incomesByPaymentMethod;
  private String totalIncome;
  private LocalDate startDate;
  private LocalDate endDate;
}
