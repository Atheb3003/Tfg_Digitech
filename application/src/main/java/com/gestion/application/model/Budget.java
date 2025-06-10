package com.gestion.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.Data;

@Entity
@Data
public class Budget {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long contactId; // En vez de @ManyToOne

  private String coordinadora;
  private LocalDate fecha;
  private LocalDate fechaCirugia;
  private String tratamientoQuirurjico;
  private String tratamientoPrevio;
  private String tecnicaQuirurjica;
  private Integer tiempoPrescripcion;
  private Integer unidadesFoliculares;
  private Double precioEspecialCirugia;
  private Double precioKit;
}
