package com.gestion.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuccessfulUpdateResponse {
    private String status;
    private String message;
}
