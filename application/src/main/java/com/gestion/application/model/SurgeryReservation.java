package com.gestion.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Entity
@Table(name = "surgery_reservation")
@Data
public class SurgeryReservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idSurgeryReservation;

  @Column(columnDefinition = "TEXT")
  private String description;

  private Integer follicularUnits;
  private String surgicalTechnique;
  private LocalDate estimatedDate;
  private Boolean national; // si es nacional o internacional
  private BigDecimal deposit; //
  // crear otro que sea DineroRestante
  private BigDecimal surgeryPrice;
  private Boolean isVisible;

  @ManyToOne
  @JoinColumn(name = "id_patient")
  private Patient patient;

  @OneToOne(mappedBy = "surgeryReservation", cascade = CascadeType.ALL)
  @JsonIgnore
  private Surgery surgery;

  @Column(nullable = false)
  private Boolean confirmed = false;
}
