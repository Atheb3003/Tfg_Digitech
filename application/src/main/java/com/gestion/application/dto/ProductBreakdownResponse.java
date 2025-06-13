package com.gestion.application.dto;

import lombok.Data;
import java.util.List;

@Data
public class ProductBreakdownResponse {
    private String startDate;
    private String endDate;
    private List<ProductBreakdownItemResponse> items;
}
