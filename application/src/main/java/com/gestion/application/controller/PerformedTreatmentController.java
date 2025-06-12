package com.gestion.application.controller;

import com.gestion.application.dto.PerformedTreatmentFromProtocolRequest;
import com.gestion.application.dto.PerformedTreatmentRequest;
import com.gestion.application.dto.PerformedTreatmentResponse;
import com.gestion.application.model.PerformedTreatment;
import com.gestion.application.service.performedTreatment.PerformedTreatmentFromProtocolService;
import com.gestion.application.service.performedTreatment.PerformedTreatmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/performed-treatments")
@RequiredArgsConstructor
public class PerformedTreatmentController {

    private final PerformedTreatmentService service;
    private final PerformedTreatmentFromProtocolService fromProtocolService;


    @PostMapping
    public ResponseEntity<Void> create(@RequestBody PerformedTreatmentRequest request) {
        service.savePerformedTreatment(request);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/from-protocol")
    public ResponseEntity<String> addFromProtocol(@RequestBody PerformedTreatmentFromProtocolRequest request) {
        fromProtocolService.createFromProtocol(request);
        return ResponseEntity.ok("Tratamiento de protocolo registrado correctamente.");
    }



    @GetMapping("/by-contact/{id}")
    public ResponseEntity<List<PerformedTreatmentResponse>> getByContact(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getTreatmentsByContactId(id));
    }

}

