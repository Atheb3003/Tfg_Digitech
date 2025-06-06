package com.gestion.application.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;

@Entity
@Table(name = "revision")
@Data
public class Revision {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idRevision;

  @ManyToOne
  @JoinColumn(name = "id_patient", nullable = false)
  private Patient patient;

  @ManyToOne
  @JoinColumn(name = "id_type", nullable = false)
  private RevisionType type;

  @ManyToOne
  @JoinColumn(name = "id_protocol", nullable = false)
  private Protocol protocol;

  @Column(columnDefinition = "TEXT")
  private String observations;

  private LocalDate revisionDate;

  @Column(name = "is_visible")
  private Boolean isVisible = true;
}
