package com.gestion.application.service.photo.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.exception.GroupPhotosNotFoundException;
import com.gestion.application.model.GroupPhotos;
import com.gestion.application.repository.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.multipart.MultipartFile;

class GroupPhotosServiceImplTest {

  @Mock private GroupPhotosRepository groupPhotosRepository;
  @Mock private ContactRepository contactRepository;
  @Mock private ConsultationRepository consultationRepository;
  @Mock private RevisionRepository revisionRepository;
  @Mock private PhotoRepository photoRepository;
  @Mock private MultipartFile file;

  @InjectMocks private GroupPhotosServiceImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldThrowIfGroupNotFound() {
    when(groupPhotosRepository.findById(999)).thenReturn(Optional.empty());

    assertThrows(GroupPhotosNotFoundException.class, () -> service.getGroupPhotos(999));
  }

  @Test
  void shouldDeleteGroupPhotosAndFolder() throws IOException {
    GroupPhotos group = new GroupPhotos();
    group.setIdGroupPhotos(1);
    when(groupPhotosRepository.findById(1)).thenReturn(Optional.of(group));

    Path testFolder = Path.of(System.getProperty("user.dir"), "uploads", "photos", "group-1");
    Files.createDirectories(testFolder);
    Files.createFile(testFolder.resolve("temp.jpg"));

    service.deleteGroupPhotos(1);

    verify(groupPhotosRepository).delete(group);
    assertFalse(Files.exists(testFolder));
  }
}
