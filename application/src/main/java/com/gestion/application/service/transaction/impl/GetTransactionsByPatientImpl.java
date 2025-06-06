package com.gestion.application.service.transaction.impl;

import com.gestion.application.dto.TransactionResponse;
import com.gestion.application.exception.PatientNotFoundException;
import com.gestion.application.mapper.TransactionMapper;
import com.gestion.application.repository.PatientRepository;
import com.gestion.application.repository.TransactionRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTransactionsByPatientImpl {

  private final TransactionRepository txRepo;
  private final PatientRepository patientRepo;
  private final TransactionMapper mapper;

  public List<TransactionResponse> getTransactionsByPatient(Integer patientId) {
    // Si el paciente no existe, PatientNotFoundException (404) será lanzada aquí
    patientRepo.findById(patientId).orElseThrow(() -> new PatientNotFoundException(patientId));

    // Recupera y mapea transacciones (vacío OK)
    return txRepo.findByPatient_IdPatient(patientId).stream()
        .map(mapper::toResponse)
        .collect(Collectors.toList());
  }
}
