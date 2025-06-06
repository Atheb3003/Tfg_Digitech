package com.gestion.application.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "group_photos")
public class GroupPhotos {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idGroupPhotos;

  @ManyToOne
  @JoinColumn(name = "id_contact")
  private Contact contact;

  @ManyToOne
  @JoinColumn(name = "id_consultation")
  private Consultation consultation;

  @ManyToOne
  @JoinColumn(name = "id_revision")
  private Revision revision;

  @Column(nullable = false)
  private String title;

  private String description;

  private LocalDate creationDate;

  private Boolean isVisible;

  @OneToMany(mappedBy = "groupPhotos", cascade = CascadeType.ALL)
  private List<Photo> photos;
}
