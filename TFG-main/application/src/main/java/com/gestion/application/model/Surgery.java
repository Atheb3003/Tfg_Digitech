package com.gestion.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;

@Entity
@Table(name = "surgery")
@Data
public class Surgery {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idSurgery;

  private LocalDate date;

  @Column(length = 500)
  private String observations;

  private Boolean isVisible;

  @OneToOne
  @JoinColumn(name = "id_surgery_reservation")
  @JsonIgnore
  private SurgeryReservation surgeryReservation;
}
