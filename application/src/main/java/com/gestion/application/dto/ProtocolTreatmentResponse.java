package com.gestion.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProtocolTreatmentResponse {

  private Integer id;

  @JsonProperty("id_product")
  private Integer productId;

  @JsonProperty("product_name")
  private String productName;

  @JsonProperty("product_price")
  private BigDecimal price;

  @JsonProperty("is_paid")
  private Boolean isPaid;

  @JsonProperty("is_finished")
  private Boolean isFinished;

  @JsonProperty("detail")
  private String detail; // ← nuevo campo
}
