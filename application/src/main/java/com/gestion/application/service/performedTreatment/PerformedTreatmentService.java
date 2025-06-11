package com.gestion.application.service.performedTreatment;

import com.gestion.application.dto.PerformedTreatmentRequest;
import com.gestion.application.dto.PerformedTreatmentResponse;
import com.gestion.application.model.Contact;
import com.gestion.application.model.PerformedTreatment;
import com.gestion.application.model.Product;
import com.gestion.application.model.ProtocolTreatment;
import com.gestion.application.repository.ContactRepository;
import com.gestion.application.repository.PerformedTreatmentRepository;
import com.gestion.application.repository.ProductRepository;
import com.gestion.application.repository.ProtocolTreatmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerformedTreatmentService {

    private final PerformedTreatmentRepository repository;
    private final ContactRepository contactRepository;
    private final ProductRepository productRepository;
    private final ProtocolTreatmentRepository protocolTreatmentRepository;

    public PerformedTreatment createTreatment(PerformedTreatmentRequest request) {
        PerformedTreatment treatment = new PerformedTreatment();

        Contact contact = contactRepository.findById(request.getContactId())
                .orElseThrow(() -> new RuntimeException("Contacto no encontrado"));

        treatment.setContact(contact);
        treatment.setPerformedDate(request.getPerformedDate());
        treatment.setFinalPrice(request.getFinalPrice());
        treatment.setNotes(request.getNotes());

        if (request.getProductId() != null) {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            treatment.setProduct(product);
        } else if (request.getProtocolTreatmentId() != null) {
            ProtocolTreatment pt = protocolTreatmentRepository.findById(request.getProtocolTreatmentId())
                    .orElseThrow(() -> new RuntimeException("Tratamiento de protocolo no encontrado"));
            treatment.setProtocolTreatment(pt);
        } else {
            throw new RuntimeException("Debe especificar un producto o tratamiento de protocolo");
        }

        return repository.save(treatment);
    }

    public List<PerformedTreatmentResponse> getTreatmentsByContactId(Integer contactId) {
        List<PerformedTreatment> treatments = repository.findByContactIdContactOrderByPerformedDateDesc(contactId);

        return treatments.stream().map(treatment -> {
            String productName = null;
            boolean isFromProtocol = false;

            if (treatment.getProduct() != null) {
                productName = treatment.getProduct().getName();
            } else if (treatment.getProtocolTreatment() != null) {
                Product protocolProduct = treatment.getProtocolTreatment().getProduct();
                if (protocolProduct != null) {
                    productName = protocolProduct.getName();
                    isFromProtocol = true;
                }
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


