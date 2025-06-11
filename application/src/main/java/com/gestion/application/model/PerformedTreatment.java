package com.gestion.application.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;

@Entity
@Table(name = "performed_treatment")
@Data
public class PerformedTreatment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "id_contact")
  private Contact contact;

  @ManyToOne
  @JoinColumn(name = "id_product", nullable = true)
  private Product product;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_revision", nullable = true)
  private Revision revision;


  @Column(name = "id_protocol_treatment")
  private Integer protocolTreatmentId;


  private LocalDate performedDate; // Fecha en la que se realizó el tratamiento
  private Double finalPrice;
  private String notes;

  private LocalDate creationDate;


}
