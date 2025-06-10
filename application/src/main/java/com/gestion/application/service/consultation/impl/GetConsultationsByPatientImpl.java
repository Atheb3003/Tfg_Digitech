// package com.gestion.application.service.consultation.impl;
//
// import com.gestion.application.dto.ConsultationResponse;
// import com.gestion.application.exception.PatientNotFoundException;
// import com.gestion.application.mapper.ConsultationMapper;
// import com.gestion.application.repository.ConsultationRepository;
// import com.gestion.application.repository.PatientRepository;
// import java.util.List;
// import java.util.stream.Collectors;
// import lombok.RequiredArgsConstructor;
// import org.springframework.stereotype.Service;
//
// @Service
// @RequiredArgsConstructor
// public class GetConsultationsByPatientImpl {
//
//  private final ConsultationRepository repo;
//  private final PatientRepository patientRepo;
//  private final ConsultationMapper mapper;
//
//  public List<ConsultationResponse> getByPatient(Integer patientId) {
//    patientRepo.findById(patientId).orElseThrow(() -> new PatientNotFoundException(patientId));
//
//    return repo.findByPatient_IdPatient(patientId).stream()
//        .map(mapper::toResponse)
//        .collect(Collectors.toList());
//  }
// }
