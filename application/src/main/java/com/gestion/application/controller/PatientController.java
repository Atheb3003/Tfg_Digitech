package com.gestion.application.controller;

import com.gestion.application.dto.ApiResponse;
import com.gestion.application.dto.ContactResponse;
import com.gestion.application.dto.ContactToPatientResponse;
import com.gestion.application.dto.PatientResponse;
import com.gestion.application.service.contact.ContactService;
import com.gestion.application.service.patient.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

  private final ContactService contactService;

  private final PatientService patientService;

  @PostMapping("/{id}/to-patient")
  public ResponseEntity<ContactToPatientResponse> transformContactToPatient(
      @PathVariable Integer id) {
    ContactToPatientResponse response = contactService.transformarEnPaciente(id);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping()
  public ResponseEntity<ApiResponse<Page<ContactResponse>>> getPatients(Pageable pageable) {
    Page<ContactResponse> response = contactService.getContactsWithPatient(pageable);
    return ResponseEntity.ok(new ApiResponse<>("success", response));
  }

  /** GET /patients/{id} */
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<PatientResponse>> getPatientById(@PathVariable Integer id) {
    PatientResponse dto = patientService.getPatientById(id);
    return ResponseEntity.ok(new ApiResponse<>("success", dto));
  }
}
