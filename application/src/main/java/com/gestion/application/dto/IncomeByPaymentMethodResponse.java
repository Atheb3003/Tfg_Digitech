// com.gestion.application.dto.IncomeByPaymentMethodResponse.java
package com.gestion.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;
import java.util.Map;

@Data
@AllArgsConstructor
public class IncomeByPaymentMethodResponse {
    private Map<String, String> incomesByPaymentMethod;
    private String totalIncome;
    private LocalDate startDate;
    private LocalDate endDate;
}
