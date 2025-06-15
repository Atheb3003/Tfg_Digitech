package com.gestion.application.dto;

import lombok.Data;

@Data
public class ProductBreakdownItemResponse {
  private String type;
  private String name;
  private Integer quantity;
  private String income;
}
