package com.gestion.application.service.performedTreatment;

import com.gestion.application.dto.PerformedTreatmentRequest;
import com.gestion.application.dto.PerformedTreatmentResponse;
import com.gestion.application.model.*;
import com.gestion.application.repository.*;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerformedTreatmentService {

  private final PerformedTreatmentRepository performedTreatmentRepository;
  private final ProductRepository productRepository;
  private final ContactRepository contactRepository;

  public void savePerformedTreatment(PerformedTreatmentRequest request) {
    PerformedTreatment treatment = new PerformedTreatment();

    // Buscar y asignar contacto
    Contact contact =
        contactRepository
            .findById(request.getContactId())
            .orElseThrow(
                () ->
                    new RuntimeException(
                        "Contacto no encontrado con ID: " + request.getContactId()));
    treatment.setContact(contact);

    // Si hay ID de producto, buscar y guardar su nombre
    if (request.getProductId() != null) {
      Product product =
          productRepository
              .findById(request.getProductId())
              .orElseThrow(
                  () ->
                      new RuntimeException(
                          "Producto no encontrado con ID: " + request.getProductId()));
      treatment.setProductName(product.getName());
    }

    // Si hay ID de revisión, guardar directamente el ID
    if (request.getRevisionId() != null) {
      treatment.setRevisionId(request.getRevisionId());
    }

    treatment.setPerformedDate(request.getPerformedDate());
    treatment.setCreationDate(LocalDate.now());

    performedTreatmentRepository.save(treatment);
  }

  public List<PerformedTreatmentResponse> getTreatmentsByContactId(Integer contactId) {
    List<PerformedTreatment> treatments =
        performedTreatmentRepository.findByContactIdContactOrderByPerformedDateDesc(contactId);

    return treatments.stream()
        .map(
            treatment -> {
              String productName = treatment.getProductName();
              boolean isFromProtocol = treatment.getProtocolTreatmentId() != null;
              Integer revisionId = treatment.getRevisionId();

              return new PerformedTreatmentResponse(
                  treatment.getId(),
                  treatment.getPerformedDate(),
                  treatment.getFinalPrice(),
                  treatment.getNotes(),
                  productName,
                  isFromProtocol,
                  revisionId);
            })
        .toList();
  }
}
