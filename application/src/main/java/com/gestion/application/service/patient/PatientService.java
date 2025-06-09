package com.gestion.application.service.patient;

import com.gestion.application.dto.ContactResponse;
import com.gestion.application.dto.PatientResponse;
import com.gestion.application.service.patient.impl.GetPatientByContactIdImpl;
import com.gestion.application.service.patient.impl.GetPatientByIdImpl;
import com.gestion.application.service.patient.impl.SearchPatientImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {

  private final GetPatientByIdImpl getPatientByIdImpl;
  private final SearchPatientImpl searchPatientImpl;
  private final GetPatientByContactIdImpl getByContactIdImpl;


  public PatientResponse getPatient(Integer id) {
    return getPatientByIdImpl.getPatientById(id);
  }

  public Page<ContactResponse> searchContacts(String term, Pageable pageable) {
    return searchPatientImpl.search(term, pageable);
  }

  public Optional<Integer> getPatientIdByContact(Integer contactId) {
    return getByContactIdImpl.getPatientIdByContact(contactId);
  }
}
