package com.gestion.application.service.protocol.impl;

import com.gestion.application.dto.UpdateProtocolRequest;
import com.gestion.application.exception.ContactNotFoundException;
import com.gestion.application.exception.ProtocolNotFoundException;
import com.gestion.application.model.Contact;
import com.gestion.application.model.Product;
import com.gestion.application.model.Protocol;
import com.gestion.application.model.ProtocolTreatment;
import com.gestion.application.repository.ContactRepository;
import com.gestion.application.repository.ProductRepository;
import com.gestion.application.repository.ProtocolRepository;
import com.gestion.application.repository.ProtocolTreatmentRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateProtocolImpl {

  private final ProtocolRepository protocolRepository;
  private final ProductRepository productRepository;
  private final ContactRepository contactRepository;
  private final ProtocolTreatmentRepository protocolTreatmentRepository;

  @Transactional
  public Protocol updateProtocol(UpdateProtocolRequest request) {
    Protocol protocol =
        protocolRepository
            .findById(request.getIdProtocol())
            .orElseThrow(() -> new ProtocolNotFoundException(request.getIdProtocol()));

    protocol.setDescription(request.getDescription());
    protocol.setPrice(request.getPrice());

    if (!protocol.getContact().getIdContact().equals(request.getContactId())) {
      Contact contact =
          contactRepository
              .findById(request.getContactId())
              .orElseThrow(() -> new ContactNotFoundException(request.getContactId()));
      protocol.setContact(contact);
    }

    protocolTreatmentRepository.deleteAll(protocol.getTreatments());

    List<ProtocolTreatment> newTreatments =
        request.getProductIds().stream()
            .map(
                productId -> {
                  Product product =
                      productRepository
                          .findById(productId)
                          .orElseThrow(
                              () -> new RuntimeException("Producto no encontrado: " + productId));
                  ProtocolTreatment pt = new ProtocolTreatment();
                  pt.setProtocol(protocol);
                  pt.setProduct(product);
                  pt.setIsFinished(false);
                  return pt;
                })
            .toList();

    List<ProtocolTreatment> savedTreatments = protocolTreatmentRepository.saveAll(newTreatments);
    protocol.setTreatments(savedTreatments);
    protocol.setIsFinished(false);

    return protocolRepository.save(protocol);
  }
}
