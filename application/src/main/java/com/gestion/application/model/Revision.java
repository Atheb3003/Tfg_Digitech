package com.gestion.application.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "revision")
@Getter
@Setter
public class Revision {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_revision")
  private Integer idRevision;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_contact", nullable = false)
  private Contact contact;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_type", nullable = false)
  private RevisionType type;

  @Column(name = "observations", nullable = false, length = 1000)
  private String observations;

  @Column(name = "revision_date", nullable = false)
  private LocalDate revisionDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_protocol")
  private Protocol protocol;

  @Column(name = "id_patient", nullable = true)
  private Integer idPatient;

  @Column(name = "is_visible", nullable = false)
  private Boolean isVisible;
}
