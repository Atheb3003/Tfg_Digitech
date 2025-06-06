package com.gestion.application.service.consultationtype;

import com.gestion.application.dto.ConsultationTypeRequest;
import com.gestion.application.dto.ConsultationTypeResponse;
import com.gestion.application.service.consultationtype.impl.*;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsultationTypeService {

  private final CreateConsultationTypeImpl createImpl;
  private final GetAllConsultationTypesImpl allImpl;
  private final GetConsultationTypeByIdImpl getByIdImpl;
  private final UpdateConsultationTypeImpl updateImpl;
  private final DeleteConsultationTypeImpl deleteImpl;

  public ConsultationTypeResponse createConsultationType(ConsultationTypeRequest req) {
    return createImpl.create(req);
  }

  public List<ConsultationTypeResponse> getAllConsultationTypes() {
    return allImpl.getAll();
  }

  public ConsultationTypeResponse getConsultationTypeById(Integer id) {
    return getByIdImpl.getById(id);
  }

  public ConsultationTypeResponse updateConsultationType(Integer id, ConsultationTypeRequest req) {
    return updateImpl.update(id, req);
  }

  public void deleteConsultationType(Integer id) {
    deleteImpl.delete(id);
  }
}
