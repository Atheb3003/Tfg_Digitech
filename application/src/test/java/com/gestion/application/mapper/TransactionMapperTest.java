package com.gestion.application.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.gestion.application.dto.TransactionRequest;
import com.gestion.application.dto.TransactionResponse;
import com.gestion.application.model.Patient;
import com.gestion.application.model.Transaction;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class TransactionMapperTest {

  private final TransactionMapper mapper = new TransactionMapper();

  @Test
  void testToEntity() {
    TransactionRequest request = new TransactionRequest();
    request.setTransactionDate(LocalDate.of(2025, 6, 14));
    request.setAmount(250.00);
    request.setDescription("Pago de tratamiento");

    request.setIdPatient(123);

    Transaction entity = mapper.toEntity(request);

    assertEquals(LocalDate.of(2025, 6, 14), entity.getTransactionDate());
    assertEquals(250.00, entity.getAmount());
    assertEquals("Pago de tratamiento", entity.getDescription());

    assertNotNull(entity.getPatient());
    assertEquals(123, entity.getPatient().getIdPatient());

    assertTrue(entity.getIsVisible());
  }

  @Test
  void testToResponse() {
    Patient patient = new Patient();
    patient.setIdPatient(456);

    Transaction entity = new Transaction();
    entity.setIdTransaction(789);
    entity.setTransactionDate(LocalDate.of(2025, 7, 1));
    entity.setAmount(350.00);
    entity.setDescription("Pago consulta");
    entity.setIsVisible(true);
    entity.setPatient(patient);

    TransactionResponse response = mapper.toResponse(entity);

    assertEquals(789, response.getIdTransaction());
    assertEquals(LocalDate.of(2025, 7, 1), response.getTransactionDate());
    assertEquals(350.00, response.getAmount());
    assertEquals("Pago consulta", response.getDescription());
    assertTrue(response.getIsVisible());
    assertEquals(456, response.getIdPatient());
  }
}
