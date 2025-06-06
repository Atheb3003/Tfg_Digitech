package com.gestion.application.service.photo;

import com.gestion.application.dto.CreateGroupPhotosRequest;
import com.gestion.application.dto.GroupPhotosResponse;
import com.gestion.application.model.GroupPhotos;
import com.gestion.application.model.Photo;
import com.gestion.application.service.photo.impl.GroupPhotosServiceImpl;
import com.gestion.application.service.photo.impl.UploadPhotoImpl;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class PhotoService {

  private final UploadPhotoImpl uploadPhoto;
  private final GroupPhotosServiceImpl groupPhotosService;

  public Photo uploadPhoto(Integer groupId, MultipartFile file) throws IOException {
    return uploadPhoto.upload(groupId, file);
  }

  public GroupPhotos createGroupWithPhotos(CreateGroupPhotosRequest request, MultipartFile[] files)
      throws IOException {
    return groupPhotosService.createGroupWithPhotos(request, files);
  }

  public GroupPhotosResponse getGroupPhotos(Integer groupId) {
    return groupPhotosService.getGroupPhotos(groupId);
  }

  public List<GroupPhotosResponse> getGroupsByContact(Integer contactId) {
    return groupPhotosService.getAllGroupsByContact(contactId);
  }

  public void deleteGroup(Integer groupId) throws IOException {
    groupPhotosService.deleteGroupPhotos(groupId);
  }

  public void deletePhotoById(Integer photoId) throws IOException {
    uploadPhoto.deletePhoto(photoId);
  }
}
