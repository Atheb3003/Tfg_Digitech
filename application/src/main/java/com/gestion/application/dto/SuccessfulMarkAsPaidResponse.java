package com.gestion.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuccessfulMarkAsPaidResponse {
    private String status;
    private String message;
}
