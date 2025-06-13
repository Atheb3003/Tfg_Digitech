// com.gestion.application.dto.TotalIncomeResponse.java

package com.gestion.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TotalIncomeResponse {
    private Double totalIncome;
    private LocalDate startDate;
    private LocalDate endDate;
}
