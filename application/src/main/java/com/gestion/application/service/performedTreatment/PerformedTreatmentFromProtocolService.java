package com.gestion.application.service.performedTreatment;

import com.gestion.application.dto.PerformedTreatmentFromProtocolRequest;
import com.gestion.application.model.Contact;
import com.gestion.application.model.PerformedTreatment;
import com.gestion.application.model.ProtocolTreatment;
import com.gestion.application.repository.ContactRepository;
import com.gestion.application.repository.PerformedTreatmentRepository;
import com.gestion.application.repository.ProtocolTreatmentRepository;
import com.gestion.application.repository.RevisionRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerformedTreatmentFromProtocolService {

  private final PerformedTreatmentRepository performedTreatmentRepository;
  private final ContactRepository contactRepository;
  private final RevisionRepository revisionRepository;
  private final ProtocolTreatmentRepository protocolTreatmentRepository;

  @Transactional
  public void createFromProtocol(PerformedTreatmentFromProtocolRequest request) {
    // Validamos contacto
    Contact contact =
        contactRepository
            .findById(request.getContactId())
            .orElseThrow(() -> new RuntimeException("Contacto no encontrado"));

    // Buscamos el ProtocolTreatment para sacar el nombre del producto
    ProtocolTreatment protocolTreatment =
        protocolTreatmentRepository
            .findById(request.getProtocolTreatmentId())
            .orElseThrow(() -> new RuntimeException("ProtocolTreatment no encontrado"));

    String productName =
        protocolTreatment.getProduct() != null ? protocolTreatment.getProduct().getName() : null;

    PerformedTreatment performed = new PerformedTreatment();
    performed.setContact(contact);
    performed.setPerformedDate(request.getPerformedDate());
    performed.setFinalPrice(request.getFinalPrice());
    performed.setNotes(request.getNotes());
    performed.setCreationDate(LocalDate.now());
    performed.setProtocolTreatmentId(request.getProtocolTreatmentId());
    performed.setProductName(productName);

    // Ahora solo asignamos el ID directamente
    if (request.getRevisionId() != null) {
      performed.setRevisionId(request.getRevisionId());
    }

    performedTreatmentRepository.save(performed);
  }
}
