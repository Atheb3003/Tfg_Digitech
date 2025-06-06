package com.gestion.application.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class CreateProtocolRequest {
  private Integer contactId;
  private String description;
  private BigDecimal price;
  private List<Integer> productIds;
}
