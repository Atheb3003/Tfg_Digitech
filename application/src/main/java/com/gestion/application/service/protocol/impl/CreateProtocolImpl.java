package com.gestion.application.service.protocol.impl;

import com.gestion.application.dto.CreateProtocolRequest;
import com.gestion.application.exception.ContactNotFoundException;
import com.gestion.application.model.Contact;
import com.gestion.application.model.Product;
import com.gestion.application.model.Protocol;
import com.gestion.application.model.ProtocolTreatment;
import com.gestion.application.repository.ContactRepository;
import com.gestion.application.repository.ProductRepository;
import com.gestion.application.repository.ProtocolRepository;
import com.gestion.application.repository.ProtocolTreatmentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateProtocolImpl {

  private final ProtocolRepository protocolRepository;
  private final ProductRepository productRepository;
  private final ContactRepository contactRepository;
  private final ProtocolTreatmentRepository protocolTreatmentRepository;

  public Protocol createProtocol(CreateProtocolRequest request) {
    Contact contact =
        contactRepository
            .findById(request.getContactId())
            .orElseThrow(() -> new ContactNotFoundException(request.getContactId()));

    Protocol protocol = new Protocol();
    protocol.setContact(contact);
    protocol.setDescription(request.getDescription());
    protocol.setPrice(request.getPrice());
    protocol.setIsFinished(false);

    Protocol savedProtocol = protocolRepository.save(protocol);

    if (request.getProductIds() != null && !request.getProductIds().isEmpty()) {
      List<ProtocolTreatment> treatments =
          request.getProductIds().stream()
              .map(
                  productId -> {
                    Product product =
                        productRepository
                            .findById(productId)
                            .orElseThrow(
                                () -> new RuntimeException("Producto no encontrado: " + productId));

                    ProtocolTreatment pt = new ProtocolTreatment();
                    pt.setProtocol(savedProtocol);
                    pt.setProduct(product);
                    pt.setIsFinished(false);
                    return pt;
                  })
              .toList();

      protocolTreatmentRepository.saveAll(treatments);
      savedProtocol.setTreatments(treatments);
    }

    return savedProtocol;
  }
}
