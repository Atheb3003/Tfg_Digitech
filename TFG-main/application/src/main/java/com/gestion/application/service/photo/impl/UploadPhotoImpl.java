package com.gestion.application.service.photo.impl;

import com.gestion.application.exception.PhotoNotFoundException;
import com.gestion.application.model.GroupPhotos;
import com.gestion.application.model.Photo;
import com.gestion.application.repository.GroupPhotosRepository;
import com.gestion.application.repository.PhotoRepository;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class UploadPhotoImpl {

  private final PhotoRepository photoRepository;
  private final GroupPhotosRepository groupPhotosRepository;

  public Photo upload(Integer groupPhotosId, MultipartFile file) throws IOException {
    GroupPhotos group =
        groupPhotosRepository
            .findById(groupPhotosId)
            .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

    String folderPath = "uploads/photos/group-" + group.getIdGroupPhotos();
    Files.createDirectories(Paths.get(folderPath));

    String filename = UUID.randomUUID() + "-" + file.getOriginalFilename();
    Path filePath = Paths.get(folderPath, filename);
    file.transferTo(filePath);

    String relativePath = "photos/group-" + group.getIdGroupPhotos() + "/" + filename;

    Photo photo = new Photo();
    photo.setGroupPhotos(group);
    photo.setFileRoute(relativePath);
    photo.setIsVisible(true);

    return photoRepository.save(photo);
  }

  public void deletePhoto(Integer photoId) throws IOException {
    Photo photo =
        photoRepository.findById(photoId).orElseThrow(() -> new PhotoNotFoundException(photoId));

    // Construir la ruta absoluta al archivo
    Path filePath = Paths.get(System.getProperty("user.dir"), "uploads", photo.getFileRoute());

    if (Files.exists(filePath)) {
      Files.delete(filePath);
    }

    photoRepository.delete(photo);
  }
}
