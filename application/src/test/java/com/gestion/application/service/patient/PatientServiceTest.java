package com.gestion.application.service.patient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ContactResponse;
import com.gestion.application.dto.PatientResponse;
import com.gestion.application.service.patient.impl.GetPatientByContactIdImpl;
import com.gestion.application.service.patient.impl.GetPatientByIdImpl;
import com.gestion.application.service.patient.impl.SearchPatientImpl;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

class PatientServiceTest {

  @Mock private GetPatientByIdImpl getPatientByIdImpl;

  @Mock private SearchPatientImpl searchPatientImpl;

  @Mock private GetPatientByContactIdImpl getByContactIdImpl;

  @InjectMocks private PatientService patientService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldReturnPatientResponse_whenGetPatientByIdIsCalled() {
    // Given
    Integer id = 1;
    PatientResponse expected = new PatientResponse();
    when(getPatientByIdImpl.getPatientById(id)).thenReturn(expected);

    // When
    PatientResponse result = patientService.getPatient(id);

    // Then
    assertNotNull(result);
    assertEquals(expected, result);
    verify(getPatientByIdImpl).getPatientById(id);
  }

  @Test
  void shouldReturnPageOfContactResponses_whenSearchContactsIsCalled() {
    // Given
    String term = "test";
    Pageable pageable = PageRequest.of(0, 10);
    Page<ContactResponse> expectedPage =
        new PageImpl<>(Collections.singletonList(new ContactResponse()));
    when(searchPatientImpl.search(term, pageable)).thenReturn(expectedPage);

    // When
    Page<ContactResponse> result = patientService.searchContacts(term, pageable);

    // Then
    assertNotNull(result);
    assertEquals(1, result.getContent().size());
    verify(searchPatientImpl).search(term, pageable);
  }

  @Test
  void shouldReturnPatientId_whenGetPatientIdByContactIsCalled() {
    // Given
    Integer contactId = 5;
    Optional<Integer> expected = Optional.of(10);
    when(getByContactIdImpl.getPatientIdByContact(contactId)).thenReturn(expected);

    // When
    Optional<Integer> result = patientService.getPatientIdByContact(contactId);

    // Then
    assertTrue(result.isPresent());
    assertEquals(expected.get(), result.get());
    verify(getByContactIdImpl).getPatientIdByContact(contactId);
  }
}
