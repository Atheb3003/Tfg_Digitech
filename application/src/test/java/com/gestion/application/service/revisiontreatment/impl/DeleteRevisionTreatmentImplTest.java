package com.gestion.application.service.revisiontreatment.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.exception.RevisionTreatmentNotFoundException;
import com.gestion.application.repository.RevisionTreatmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class DeleteRevisionTreatmentImplTest {

  @Mock private RevisionTreatmentRepository repo;

  @InjectMocks private DeleteRevisionTreatmentImpl service;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void delete_existingId_deletesSuccessfully() {
    Integer id = 5;

    when(repo.existsById(id)).thenReturn(true);
    doNothing().when(repo).deleteById(id);

    service.delete(id);

    verify(repo).existsById(id);
    verify(repo).deleteById(id);
  }

  @Test
  void delete_nonExistingId_throwsRevisionTreatmentNotFoundException() {
    Integer id = 5;

    when(repo.existsById(id)).thenReturn(false);

    RevisionTreatmentNotFoundException ex =
        assertThrows(RevisionTreatmentNotFoundException.class, () -> service.delete(id));

    assertTrue(ex.getMessage().contains(id.toString()));

    verify(repo).existsById(id);
    verify(repo, never()).deleteById(any());
  }
}
