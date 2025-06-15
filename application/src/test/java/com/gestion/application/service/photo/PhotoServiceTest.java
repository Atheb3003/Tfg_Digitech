package com.gestion.application.service.photo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.CreateGroupPhotosRequest;
import com.gestion.application.dto.GroupPhotosResponse;
import com.gestion.application.model.GroupPhotos;
import com.gestion.application.model.Photo;
import com.gestion.application.service.photo.impl.GroupPhotosServiceImpl;
import com.gestion.application.service.photo.impl.UploadPhotoImpl;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.multipart.MultipartFile;

class PhotoServiceTest {

  @Mock private UploadPhotoImpl uploadPhoto;

  @Mock private GroupPhotosServiceImpl groupPhotosService;

  @InjectMocks private PhotoService photoService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldUploadPhoto() throws IOException {
    MultipartFile file = mock(MultipartFile.class);
    Photo photo = new Photo();
    when(uploadPhoto.upload(1, file)).thenReturn(photo);

    Photo result = photoService.uploadPhoto(1, file);

    assertEquals(photo, result);
    verify(uploadPhoto).upload(1, file);
  }

  @Test
  void shouldCreateGroupWithPhotos() throws IOException {
    CreateGroupPhotosRequest request = new CreateGroupPhotosRequest();
    MultipartFile[] files = new MultipartFile[0];
    GroupPhotos group = new GroupPhotos();

    when(groupPhotosService.createGroupWithPhotos(request, files)).thenReturn(group);

    GroupPhotos result = photoService.createGroupWithPhotos(request, files);

    assertEquals(group, result);
    verify(groupPhotosService).createGroupWithPhotos(request, files);
  }

  @Test
  void shouldReturnGroupPhotosById() {
    GroupPhotosResponse response = new GroupPhotosResponse();
    when(groupPhotosService.getGroupPhotos(5)).thenReturn(response);

    GroupPhotosResponse result = photoService.getGroupPhotos(5);

    assertEquals(response, result);
    verify(groupPhotosService).getGroupPhotos(5);
  }

  @Test
  void shouldReturnGroupsByContact() {
    List<GroupPhotosResponse> list = List.of(new GroupPhotosResponse());
    when(groupPhotosService.getAllGroupsByContact(2)).thenReturn(list);

    List<GroupPhotosResponse> result = photoService.getGroupsByContact(2);

    assertEquals(list, result);
    verify(groupPhotosService).getAllGroupsByContact(2);
  }

  @Test
  void shouldDeleteGroup() throws IOException {
    photoService.deleteGroup(3);
    verify(groupPhotosService).deleteGroupPhotos(3);
  }

  @Test
  void shouldDeletePhotoById() throws IOException {
    photoService.deletePhotoById(10);
    verify(uploadPhoto).deletePhoto(10);
  }
}
