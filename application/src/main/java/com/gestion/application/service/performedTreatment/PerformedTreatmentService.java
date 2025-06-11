package com.gestion.application.service.performedTreatment;

import com.gestion.application.dto.PerformedTreatmentRequest;
import com.gestion.application.dto.PerformedTreatmentResponse;
import com.gestion.application.model.*;
import com.gestion.application.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerformedTreatmentService {

    private final PerformedTreatmentRepository repository;
    private final ContactRepository contactRepository;
    private final ProductRepository productRepository;
    private final RevisionRepository revisionRepository;

    public PerformedTreatment createTreatment(PerformedTreatmentRequest request) {
        PerformedTreatment treatment = new PerformedTreatment();

        // Validar y asignar contacto
        Contact contact = contactRepository.findById(request.getContactId())
                .orElseThrow(() -> new RuntimeException("Contacto no encontrado"));
        treatment.setContact(contact);

        // Validar y asignar producto (opcional)
        if (request.getProductId() != null) {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            treatment.setProduct(product);
        }

        // Validar y asignar revisión (opcional)
        if (request.getRevisionId() != null) {
            Revision revision = revisionRepository.findById(request.getRevisionId())
                    .orElseThrow(() -> new RuntimeException("Revisión no encontrada"));
            treatment.setRevision(revision);
        }

        // Asignar fecha de realización
        treatment.setPerformedDate(request.getPerformedDate());

        return repository.save(treatment);
    }
    public List<PerformedTreatmentResponse> getTreatmentsByContactId(Integer contactId) {
        List<PerformedTreatment> treatments = repository.findByContactIdContactOrderByPerformedDateDesc(contactId);

        return treatments.stream().map(treatment -> {
            String productName = null;
            boolean isFromProtocol = false;

            if (treatment.getProduct() != null) {
                productName = treatment.getProduct().getName();
            }

            // Si existe un ID de protocolo, marcamos como "desde protocolo"
            if (treatment.getProtocolTreatmentId() != null) {
                isFromProtocol = true;
            }

            return new PerformedTreatmentResponse(
                    treatment.getId(),
                    treatment.getPerformedDate(),
                    treatment.getFinalPrice(),
                    treatment.getNotes(),
                    productName,
                    isFromProtocol
            );
        }).toList();
    }

}


