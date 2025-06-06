package com.gestion.application.service.contact;

import com.gestion.application.dto.ContactRequest;
import com.gestion.application.dto.ContactResponse;
import com.gestion.application.dto.ContactToPatientResponse;
import com.gestion.application.mapper.ContactMapper;
import com.gestion.application.model.Contact;
import com.gestion.application.service.contact.impl.*;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService {

  private final CreateContactImpl createContact;
  private final EditContactImpl editContact;
  private final DeleteContactImpl deleteContact;
  private final GetContactAllDetailsImpl getContactAllDetails;
  private final TransformContactToPatientImpl transformContactToPatient;
  private final GetContactsWithPatientImpl getContactsWithPatient;
  private final GetContactsWithoutPatientImpl getContactsWithoutPatient;
  private final ContactMapper contactMapper;
  private final SearchContactsImpl searchImpl;

  public Contact crearContacto(Contact c) {
    return createContact.crearContacto(c);
  }

  public Contact editarContacto(Integer id, ContactRequest request) {
    return editContact.editContact(id, request);
  }

  public void deleteContact(Integer id) {
    deleteContact.deleteContact(id);
  }

  public ContactResponse getContactAllDetails(Integer id) {
    return getContactAllDetails.getContactById(id);
  }

  public ContactToPatientResponse transformarEnPaciente(Integer idContacto) {
    return transformContactToPatient.transformContactToPatient(idContacto);
  }

  public Page<ContactResponse> getContactsWithPatient(Pageable pageable) {
    return getContactsWithPatient.getContactsWithPatient(pageable);
  }

  public Page<ContactResponse> getContactsWithoutPatient(Pageable pageable) {
    return getContactsWithoutPatient.getContactsWithoutPatient(pageable);
  }

  /** GET /contacts/search/{term} */
  public List<ContactResponse> searchContacts(String term) {
    return searchImpl.search(term);
  }
}
