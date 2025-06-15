package com.gestion.application.dto;

import java.util.List;
import lombok.Data;

@Data
public class ProductBreakdownResponse {
  private String startDate;
  private String endDate;
  private List<ProductBreakdownItemResponse> items;
}
