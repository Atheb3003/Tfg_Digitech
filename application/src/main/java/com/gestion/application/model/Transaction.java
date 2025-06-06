package com.gestion.application.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;

@Entity
@Table(name = "transaction")
@Data
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idTransaction;

  private LocalDate transactionDate;

  private Double amount;

  @Column(columnDefinition = "TEXT")
  private String description;

  private Boolean isVisible;

  @ManyToOne
  @JoinColumn(name = "id_patient", nullable = false)
  private Patient patient;
}
