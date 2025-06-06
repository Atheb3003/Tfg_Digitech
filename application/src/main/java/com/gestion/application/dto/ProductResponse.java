package com.gestion.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.Data;

/** DTO de respuesta para productos */
@Data
public class ProductResponse {
  @JsonProperty("id_product")
  private Integer idProduct;

  private String name;
  private String description;
  private Double price;

  @JsonProperty("creation_date")
  private LocalDate creationDate;

  @JsonProperty("update_date")
  private LocalDate updateDate;

  @JsonProperty("id_productType")
  private Integer idProductType;

  @JsonProperty("is_visible")
  private Boolean isVisible;
}
