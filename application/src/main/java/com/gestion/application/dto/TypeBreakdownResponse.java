package com.gestion.application.dto;

import lombok.Data;
import java.util.List;

@Data
public class TypeBreakdownResponse {
    private List<TypeSummary> types;
    private String startDate;
    private String endDate;

    @Data
    public static class TypeSummary {
        private String type;
        private int quantity;
        private String totalIncome;
    }
}
