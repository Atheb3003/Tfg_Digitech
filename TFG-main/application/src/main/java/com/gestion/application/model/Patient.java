package com.gestion.application.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Entity
@Table(name = "patient")
@Data
public class Patient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idPatient;

  @OneToOne
  @JoinColumn(name = "id_contact", referencedColumnName = "idContact", nullable = false)
  private Contact contact;

  private LocalDate dischargeDate;

  private Boolean isVisible;

  // Todas las transacciones asociadas a este paciente
  @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
  private List<Transaction> transactions;
}
