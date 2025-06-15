package com.gestion.application.dto;

import java.util.List;
import lombok.Data;

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
