package com.gestion.application.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.*;
import com.gestion.application.model.GroupPhotos;
import com.gestion.application.model.Photo;
import com.gestion.application.service.photo.PhotoService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

class PhotoControllerTest {

  @Mock private PhotoService photoService;

  @InjectMocks private PhotoController controller;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testUploadPhoto_success() throws Exception {
    MultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "data".getBytes());
    Photo mockPhoto = new Photo(); // Simula entidad
    when(photoService.uploadPhoto(1, file)).thenReturn(mockPhoto);

    ResponseEntity<?> response = controller.uploadPhoto(1, file);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(mockPhoto, response.getBody());
  }

  @Test
  void testUploadPhoto_failure() throws Exception {
    MultipartFile file = new MockMultipartFile("file", "bad.jpg", "image/jpeg", "data".getBytes());
    when(photoService.uploadPhoto(1, file)).thenThrow(new RuntimeException("Fallo"));

    ResponseEntity<?> response = controller.uploadPhoto(1, file);

    assertEquals(500, response.getStatusCodeValue());
    Map<?, ?> body = (Map<?, ?>) response.getBody();
    assertTrue(body.containsKey("error"));
  }

  @Test
  void testCreateGroupWithPhotos_success() throws Exception {
    CreateGroupPhotosRequest request = new CreateGroupPhotosRequest();
    MultipartFile[] files = {
      new MockMultipartFile("files", "img1.jpg", "image/jpeg", "123".getBytes()),
      new MockMultipartFile("files", "img2.jpg", "image/jpeg", "456".getBytes())
    };

    GroupPhotos group = new GroupPhotos();
    group.setIdGroupPhotos(10);
    group.setPhotos(List.of(new Photo(), new Photo()));

    when(photoService.createGroupWithPhotos(request, files)).thenReturn(group);

    ResponseEntity<?> response = controller.createGroupWithPhotos(request, files);

    assertEquals(200, response.getStatusCodeValue());
    Map<?, ?> body = (Map<?, ?>) response.getBody();
    assertEquals(10, body.get("groupId"));
    assertEquals(2, body.get("photosUploaded"));
  }

  @Test
  void testCreateGroupWithPhotos_failure() throws Exception {
    CreateGroupPhotosRequest request = new CreateGroupPhotosRequest();
    MultipartFile[] files = {
      new MockMultipartFile("files", "bad.jpg", "image/jpeg", "data".getBytes())
    };

    when(photoService.createGroupWithPhotos(request, files)).thenThrow(new IOException("Error"));

    ResponseEntity<?> response = controller.createGroupWithPhotos(request, files);

    assertEquals(500, response.getStatusCodeValue());
    assertTrue(((Map<?, ?>) response.getBody()).containsKey("error"));
  }

  @Test
  void testGetPhotosByGroup() {
    GroupPhotosResponse mockResponse = new GroupPhotosResponse();
    when(photoService.getGroupPhotos(1)).thenReturn(mockResponse);

    ResponseEntity<GroupPhotosResponse> response = controller.getPhotosByGroup(1);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(mockResponse, response.getBody());
  }

  @Test
  void testGetGroupsByContact() {
    GroupPhotosResponse r1 = new GroupPhotosResponse();
    GroupPhotosResponse r2 = new GroupPhotosResponse();

    when(photoService.getGroupsByContact(5)).thenReturn(List.of(r1, r2));

    ResponseEntity<ApiResponse<List<GroupPhotosResponse>>> response =
        controller.getGroupsByContact(5);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(2, response.getBody().getData().size());
  }

  @Test
  void testDeleteGroup() throws Exception {
    doNothing().when(photoService).deleteGroup(10);

    ResponseEntity<?> response = controller.deleteGroup(10);

    assertEquals(200, response.getStatusCodeValue());
    assertTrue(((Map<?, ?>) response.getBody()).get("message").toString().contains("eliminado"));
  }

  @Test
  void testDeletePhoto() throws Exception {
    doNothing().when(photoService).deletePhotoById(100);

    ResponseEntity<?> response = controller.deletePhoto(100);

    assertEquals(200, response.getStatusCodeValue());
    assertTrue(((Map<?, ?>) response.getBody()).get("message").toString().contains("eliminada"));
  }
}
