package com.gestion.application.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "revision_treatment")
@Data
public class RevisionTreatment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "id_revision")
  private Revision revision;

  @ManyToOne
  @JoinColumn(name = "id_product")
  private Product product;
}
