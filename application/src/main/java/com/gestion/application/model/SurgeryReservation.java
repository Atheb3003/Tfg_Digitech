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
  private BigDecimal deposit; // aquí cada vez que se haga un pago, se irá sumando aquí

  @Column(name = "money_remaining")
  private BigDecimal remainingMoney; // diferencia entre precio de la cirugía y depósito

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

  @Column(name = "is_paid", nullable = false)
  private Boolean isPaid = false; // cuando se haya pagado toda la cirugía, se pondrá en true
}
