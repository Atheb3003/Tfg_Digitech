package com.gestion.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductTypeRequest {

  @JsonProperty("type_product")
  private String typeProduct;
}
