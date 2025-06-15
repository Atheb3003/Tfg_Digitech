package com.gestion.application.service.protocol.impl;

import com.gestion.application.dto.ProtocolSearchResponseDto;
import com.gestion.application.dto.ProtocolSearchTreatmentDto;
import com.gestion.application.model.Protocol;
import com.gestion.application.model.ProtocolTreatment;
import com.gestion.application.repository.ProtocolRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchProtocolsImpl {

  private final ProtocolRepository protocolRepository;

  public Page<ProtocolSearchResponseDto> searchProtocols(String search, Pageable pageable) {
    Page<Protocol> protocols = protocolRepository.searchProtocols(search, pageable);
    return protocols.map(this::mapToDto);
  }

  private ProtocolSearchResponseDto mapToDto(Protocol protocol) {
    ProtocolSearchResponseDto dto = new ProtocolSearchResponseDto();
    dto.setProtocolId(protocol.getIdProtocol());
    dto.setProtocolDescription(protocol.getDescription());
    dto.setFinished(protocol.getIsFinished());
    dto.setContactIdString(
        protocol.getContact() != null ? protocol.getContact().getIdContactString() : null);
    dto.setProtocolTotalPrice(
        protocol.getTreatments() != null
            ? protocol.getTreatments().stream()
                .map(ProtocolTreatment::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
            : BigDecimal.ZERO);
    List<ProtocolSearchTreatmentDto> treatmentsDto =
        protocol.getTreatments() != null
            ? protocol.getTreatments().stream()
                .map(this::mapTreatmentToDto)
                .collect(Collectors.toList())
            : List.of();
    dto.setTreatments(treatmentsDto);
    return dto;
  }

  private ProtocolSearchTreatmentDto mapTreatmentToDto(ProtocolTreatment treatment) {
    ProtocolSearchTreatmentDto dto = new ProtocolSearchTreatmentDto();
    dto.setTreatmentId(treatment.getId());
    dto.setTreatmentPrice(treatment.getPrice());
    dto.setProductName(treatment.getProduct() != null ? treatment.getProduct().getName() : null);
    dto.setPaid(treatment.getIsPaid());
    dto.setComplete(treatment.getIsFinished());

    return dto;
  }
}
