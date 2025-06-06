package com.gestion.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductTypeResponse {

  @JsonProperty("id_typeProduct")
  private Integer idProductType;

  @JsonProperty("type_product")
  private String typeProduct;
}
