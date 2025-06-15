package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class BudgetResponseTest {

  @Test
  void testRecordConstructorAndGetters() {
    LocalDate fecha = LocalDate.of(2025, 6, 14);
    LocalDate fechaCirugia = LocalDate.of(2025, 7, 1);

    BudgetResponse response =
        new BudgetResponse(
            1L,
            "Laura",
            fecha,
            fechaCirugia,
            "Tratamiento X",
            "Tratamiento Y",
            "Técnica Z",
            10,
            5,
            1500.50,
            200.75);

    assertEquals(1L, response.id());
    assertEquals("Laura", response.coordinadora());
    assertEquals(fecha, response.fecha());
    assertEquals(fechaCirugia, response.fechaCirugia());
    assertEquals("Tratamiento X", response.tratamientoQuirurjico());
    assertEquals("Tratamiento Y", response.tratamientoPrevio());
    assertEquals("Técnica Z", response.tecnicaQuirurjica());
    assertEquals(10, response.tiempoPrescripcion());
    assertEquals(5, response.unidadesFoliculares());
    assertEquals(1500.50, response.precioEspecialCirugia());
    assertEquals(200.75, response.precioKit());
  }
}
