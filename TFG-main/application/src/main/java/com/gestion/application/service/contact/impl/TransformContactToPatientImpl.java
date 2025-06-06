package com.gestion.application.service.contact.impl;

import com.gestion.application.dto.ContactToPatientResponse;
import com.gestion.application.exception.ContactNotFoundException;
import com.gestion.application.model.Contact;
import com.gestion.application.model.Patient;
import com.gestion.application.repository.ContactRepository;
import com.gestion.application.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransformContactToPatientImpl {

  private final ContactRepository contactRepository;
  private final PatientRepository patientRepository;

  public ContactToPatientResponse transformContactToPatient(Integer idContacto) {
    Contact contact =
        contactRepository
            .findById(idContacto)
            .orElseThrow(() -> new ContactNotFoundException(idContacto));

    // Verificar que aún no es paciente
    if (contact.getPatient() != null) {
      throw new IllegalStateException("Este contacto ya está vinculado a un paciente.");
    }

    Patient patient = new Patient();
    patient.setContact(contact);
    patient.setIsVisible(true);
    patientRepository.save(patient);

    return new ContactToPatientResponse(
        "created",
        "Contacto con ID "
            + idContacto
            + " transformado correctamente en paciente con ID "
            + patient.getIdPatient()
            + ".");
  }
}
