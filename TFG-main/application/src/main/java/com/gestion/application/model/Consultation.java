package com.gestion.application.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "consultation")
public class Consultation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idConsultation;

  @ManyToOne
  @JoinColumn(name = "id_contact")
  private Contact contact;

  @ManyToOne
  @JoinColumn(name = "id_type")
  private ConsultationType type;

  private Integer follicularUnits;

  @Column(columnDefinition = "TEXT")
  private String insertionZones;

  @Column(columnDefinition = "TEXT")
  private String observations;

  private Boolean treatmentDone;

  @Column(name = "surgery_reserved")
  private Boolean surgeryReserved;

  private LocalDateTime consultationDate;

  private Boolean isVisible;
}
