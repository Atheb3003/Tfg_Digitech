package com.gestion.application.service.transaction.impl;

import com.gestion.application.dto.TransactionRequest;
import com.gestion.application.dto.TransactionResponse;
import com.gestion.application.exception.PatientNotFoundException;
import com.gestion.application.exception.TransactionCreationException;
import com.gestion.application.exception.TransactionInvalidDataException;
import com.gestion.application.mapper.TransactionMapper;
import com.gestion.application.model.Patient;
import com.gestion.application.model.Transaction;
import com.gestion.application.repository.PatientRepository;
import com.gestion.application.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTransactionImpl {

  private final TransactionRepository txRepo;
  private final PatientRepository patientRepo;
  private final TransactionMapper mapper;

  public TransactionResponse create(TransactionRequest req) {
    // 1) Validar campos obligatorios
    if (req.getTransactionDate() == null
            || req.getAmount() == null
            || req.getAmount() < 0
            || req.getIdPatient() == null
            || req.getPaymentMethod() == null) {
      throw new TransactionInvalidDataException();
    }

    // 2) Verificar que el paciente existe
    Patient paciente = patientRepo
            .findById(req.getIdPatient())
            .orElseThrow(() -> new PatientNotFoundException(req.getIdPatient()));

    // 3) Mapear DTO → entidad y asignar paciente recuperado
    Transaction entity = mapper.toEntity(req);
    entity.setPatient(paciente);

    // 4) Guardar en base de datos
    try {
      Transaction saved = txRepo.save(entity);
      return mapper.toResponse(saved);
    } catch (Exception ex) {
      throw new TransactionCreationException();
    }
  }
}
