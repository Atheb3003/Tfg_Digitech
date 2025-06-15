package com.gestion.application.service.revisiontype.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.exception.RevisionTypeNotFoundException;
import com.gestion.application.repository.RevisionTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class DeleteRevisionTypeImplTest {

  @Mock private RevisionTypeRepository repo;

  @InjectMocks private DeleteRevisionTypeImpl service;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void delete_existingId_deletesSuccessfully() {
    Integer id = 3;
    when(repo.existsById(id)).thenReturn(true);
    doNothing().when(repo).deleteById(id);

    service.delete(id);

    verify(repo).existsById(id);
    verify(repo).deleteById(id);
  }

  @Test
  void delete_nonExistingId_throwsRevisionTypeNotFoundException() {
    Integer id = 3;
    when(repo.existsById(id)).thenReturn(false);

    RevisionTypeNotFoundException ex =
        assertThrows(RevisionTypeNotFoundException.class, () -> service.delete(id));

    assertTrue(ex.getMessage().contains(id.toString()));
    verify(repo).existsById(id);
    verify(repo, never()).deleteById(any());
  }
}
