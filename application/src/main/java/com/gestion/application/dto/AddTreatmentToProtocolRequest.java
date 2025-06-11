package com.gestion.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddTreatmentToProtocolRequest {

    @JsonProperty("id_protocol")
    private Integer protocolId;

    @JsonProperty("id_product")
    private Integer productId;

    private String detail;

    private BigDecimal price; // ← nuevo campo obligatorio
}
