// src/main/java/com/gestion/application/dto/SurgeryReservationRequest.java
package com.gestion.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class SurgeryReservationRequest {

    @JsonProperty("description")
    private String description;

    @JsonProperty("follicular_units")
    private Integer follicularUnits;

    @JsonProperty("surgical_technique")
    private String surgicalTechnique;

    @JsonProperty("estimated_date")
    private LocalDate estimatedDate;

    @JsonProperty("national")
    private Boolean national;

    @JsonProperty("deposit")
    private BigDecimal deposit;

    @JsonProperty("surgery_price")
    private BigDecimal surgeryPrice;

    @JsonProperty("is_visible")
    private Boolean isVisible;

    @JsonProperty("confirmed")
    private Boolean confirmed;

    @JsonProperty("id_patient")
    private Integer patientId;
}
