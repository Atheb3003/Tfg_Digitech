package com.gestion.application.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gestion.application.model.PaymentMethod;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionSearchDto {
    private String patientName;
    private Integer transactionId;
    private Double amount;
    private String patientDni;
    private String telephoneNumber;
    private String contactIdentifier;
    private LocalDate transactionDate;
    private PaymentMethod paymentMethod;
}