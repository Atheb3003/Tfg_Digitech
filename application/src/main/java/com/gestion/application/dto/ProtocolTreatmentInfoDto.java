package com.gestion.application.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProtocolTreatmentInfoDto {
    private Integer id;
    private BigDecimal price;
    private String productName;
    private Boolean isPaid;
}