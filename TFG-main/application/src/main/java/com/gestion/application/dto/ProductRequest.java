package com.gestion.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/** Body para POST /products */
@Data
public class ProductRequest {
  private String name;
  private String description;
  private Double price;

  @JsonProperty("id_productType")
  private Integer idProductType;
}
