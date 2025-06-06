package com.gestion.application.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "photo")
public class Photo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idPhoto;

  @ManyToOne
  @JoinColumn(name = "id_group_photos")
  private GroupPhotos groupPhotos;

  private String fileRoute;

  private Boolean isVisible;
}
