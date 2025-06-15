package com.gestion.application.service.photo.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.exception.PhotoNotFoundException;
import com.gestion.application.model.GroupPhotos;
import com.gestion.application.model.Photo;
import com.gestion.application.repository.GroupPhotosRepository;
import com.gestion.application.repository.PhotoRepository;
import java.io.IOException;
import java.nio.file.*;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.multipart.MultipartFile;

class UploadPhotoImplTest {

  @Mock private PhotoRepository photoRepository;

  @Mock private GroupPhotosRepository groupPhotosRepository;

  @Mock private MultipartFile multipartFile;

  @InjectMocks private UploadPhotoImpl uploadPhoto;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldUploadPhotoSuccessfully() throws IOException {
    // Given
    GroupPhotos group = new GroupPhotos();
    group.setIdGroupPhotos(1);

    when(groupPhotosRepository.findById(1)).thenReturn(Optional.of(group));
    when(multipartFile.getOriginalFilename()).thenReturn("test.jpg");
    doAnswer(
            invocation -> {
              Path path = invocation.getArgument(0);
              Files.createDirectories(path.getParent()); // Ensure directory
              Files.createFile(path); // Simulate file creation
              return null;
            })
        .when(multipartFile)
        .transferTo(any(Path.class));

    when(photoRepository.save(any(Photo.class))).thenAnswer(i -> i.getArgument(0));

    // When
    Photo savedPhoto = uploadPhoto.upload(1, multipartFile);

    // Then
    assertNotNull(savedPhoto);
    assertEquals(
        "photos/group-1/" + savedPhoto.getFileRoute().split("/")[2], savedPhoto.getFileRoute());
    verify(photoRepository).save(any(Photo.class));
  }

  @Test
  void shouldDeletePhotoSuccessfully() throws IOException {
    // Given
    Photo photo = new Photo();
    photo.setIdPhoto(1);
    photo.setFileRoute("photos/group-1/sample.jpg");

    Path filePath = Paths.get(System.getProperty("user.dir"), "uploads", photo.getFileRoute());
    Files.createDirectories(filePath.getParent());
    Files.createFile(filePath);

    when(photoRepository.findById(1)).thenReturn(Optional.of(photo));

    // When
    uploadPhoto.deletePhoto(1);

    // Then
    assertFalse(Files.exists(filePath));
    verify(photoRepository).delete(photo);
  }

  @Test
  void shouldThrowExceptionIfPhotoNotFound() {
    // Given
    when(photoRepository.findById(999)).thenReturn(Optional.empty());

    // Then
    assertThrows(PhotoNotFoundException.class, () -> uploadPhoto.deletePhoto(999));
  }
}
