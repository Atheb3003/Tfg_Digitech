package com.gestion.application.dto;

import java.time.LocalDate;

public record BudgetResponse(
    Long id,
    String coordinadora,
    LocalDate fecha,
    LocalDate fechaCirugia,
    String tratamientoQuirurjico,
    String tratamientoPrevio,
    String tecnicaQuirurjica,
    Integer tiempoPrescripcion,
    Integer unidadesFoliculares,
    Double precioEspecialCirugia,
    Double precioKit) {}
