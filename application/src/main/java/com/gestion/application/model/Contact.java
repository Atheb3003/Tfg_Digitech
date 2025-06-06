package com.gestion.application.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Entity
@Table(name = "contact")
@Data
public class Contact {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idContact;

  private String name;
  private String surname;
  private String nif;
  private LocalDate birthDate;
  private String occupation;
  private String country;
  private String province;
  private String town;
  private String direction;
  private String telephoneNumber1;
  private String telephoneNumber2;
  private String email;

  @Column(columnDefinition = "TEXT")
  private String observations;

  private Boolean isVisible;

  // Relaciones -------------------------------------

  @OneToOne(mappedBy = "contact", cascade = CascadeType.ALL)
  private Patient patient;

  @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
  private List<GroupPhotos> groupPhotos;

  // **¡Elimina esta colección!**
  // @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
  // private List<Transaction> transactions;
}
