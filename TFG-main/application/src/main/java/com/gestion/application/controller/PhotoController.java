package com.gestion.application.controller;

import com.gestion.application.dto.ApiResponse;
import com.gestion.application.dto.CreateGroupPhotosRequest;
import com.gestion.application.dto.GroupPhotosResponse;
import com.gestion.application.model.GroupPhotos;
import com.gestion.application.model.Photo;
import com.gestion.application.service.photo.PhotoService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/photos")
public class PhotoController {

  private final PhotoService photoService;

  @PostMapping("/upload/{groupId}")
  public ResponseEntity<?> uploadPhoto(
      @PathVariable Integer groupId, @RequestParam("file") MultipartFile file) {
    try {
      Photo saved = photoService.uploadPhoto(groupId, file);
      return ResponseEntity.ok(saved); // puedes envolverlo en un ApiResponse si quieres
    } catch (Exception e) {
      return ResponseEntity.status(500)
          .body(Map.of("error", "No se pudo subir la foto", "message", e.getMessage()));
    }
  }

  @PostMapping("/groups/create-with-photos")
  public ResponseEntity<?> createGroupWithPhotos(
      @ModelAttribute CreateGroupPhotosRequest request,
      @RequestParam("files") MultipartFile[] files) {
    try {
      GroupPhotos group = photoService.createGroupWithPhotos(request, files);
      return ResponseEntity.ok(
          Map.of(
              "status", "created",
              "groupId", group.getIdGroupPhotos(),
              "photosUploaded", group.getPhotos().size()));
    } catch (IOException e) {
      return ResponseEntity.internalServerError()
          .body(Map.of("error", "Error al guardar las imágenes", "message", e.getMessage()));
    } catch (Exception e) {
      return ResponseEntity.internalServerError()
          .body(Map.of("error", "Error al crear el grupo", "message", e.getMessage()));
    }
  }

  @GetMapping("/groups/{id}")
  public ResponseEntity<GroupPhotosResponse> getPhotosByGroup(@PathVariable Integer id) {
    GroupPhotosResponse response = photoService.getGroupPhotos(id);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/groups/contact/{contactId}")
  public ResponseEntity<ApiResponse<List<GroupPhotosResponse>>> getGroupsByContact(
      @PathVariable Integer contactId) {
    List<GroupPhotosResponse> responses = photoService.getGroupsByContact(contactId);
    return ResponseEntity.ok(new ApiResponse<>("success", responses));
  }

  @DeleteMapping("/groups/{groupId}")
  public ResponseEntity<?> deleteGroup(@PathVariable Integer groupId) throws IOException {
    photoService.deleteGroup(groupId);
    return ResponseEntity.ok(
        Map.of(
            "status",
            "deleted",
            "message",
            "Grupo con ID " + groupId + " eliminado correctamente."));
  }

  @DeleteMapping("/{photoId}")
  public ResponseEntity<?> deletePhoto(@PathVariable Integer photoId) throws IOException {
    photoService.deletePhotoById(photoId);
    return ResponseEntity.ok(
        Map.of(
            "status",
            "deleted",
            "message",
            "Foto con ID " + photoId + " eliminada correctamente."));
  }
}
