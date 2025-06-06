package com.gestion.application.controller;

import com.gestion.application.dto.ApiResponse;
import com.gestion.application.dto.ContactRequest;
import com.gestion.application.dto.ContactResponse;
import com.gestion.application.dto.SuccessfulDeleteResponse;
import com.gestion.application.mapper.ContactMapper;
import com.gestion.application.model.Contact;
import com.gestion.application.service.contact.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contacts")
public class ContactController {

  private final ContactService contactService;
  private final ContactMapper contactMapper;
  private final ContactService service;

  @PostMapping
  public ResponseEntity<ApiResponse<ContactResponse>> createContact(
      @RequestBody ContactRequest contactRequest) {

    Contact contacto = contactMapper.toEntity(contactRequest);
    Contact saved = contactService.crearContacto(contacto);
    ContactResponse response = contactMapper.toResponse(saved);
    ApiResponse<ContactResponse> apiResponse = new ApiResponse<>("created", response);

    return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<ContactResponse>> editContact(
      @PathVariable Integer id, @RequestBody ContactRequest contactRequest) {
    Contact updated = contactService.editarContacto(id, contactRequest);
    ContactResponse response = contactMapper.toResponse(updated);
    return ResponseEntity.ok(new ApiResponse<>("updated", response));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<SuccessfulDeleteResponse> deleteContact(@PathVariable Integer id) {
    contactService.deleteContact(id);
    SuccessfulDeleteResponse response =
        new SuccessfulDeleteResponse(
            "deleted", "Contacto con ID " + id + " eliminado correctamente.");
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ContactResponse getContactDetails(@PathVariable Integer id) {
    return contactService.getContactAllDetails(id);
  }

  @GetMapping()
  public ResponseEntity<ApiResponse<Page<ContactResponse>>> getNonPatients(Pageable pageable) {
    Page<ContactResponse> response = contactService.getContactsWithoutPatient(pageable);
    return ResponseEntity.ok(new ApiResponse<>("success", response));
  }

  @GetMapping("/search/{term}")
  public ResponseEntity<ApiResponse<Page<ContactResponse>>> searchContacts(
      @PathVariable String term, Pageable pageable) {
    Page<ContactResponse> results = service.searchContacts(term, pageable);
    return ResponseEntity.ok(new ApiResponse<>("success", results));
  }
}
