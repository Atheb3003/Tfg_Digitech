package com.gestion.application.controller;

import com.gestion.application.dto.PerformedTreatmentRequest;
import com.gestion.application.dto.PerformedTreatmentResponse;
import com.gestion.application.model.PerformedTreatment;
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

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody PerformedTreatmentRequest request) {
        service.createTreatment(request);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/by-contact/{id}")
    public ResponseEntity<List<PerformedTreatmentResponse>> getByContact(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getTreatmentsByContactId(id));
    }
}

