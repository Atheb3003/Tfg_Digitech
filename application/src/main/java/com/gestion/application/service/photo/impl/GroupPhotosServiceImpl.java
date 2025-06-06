package com.gestion.application.service.photo.impl;

import com.gestion.application.dto.CreateGroupPhotosRequest;
import com.gestion.application.dto.GroupPhotosResponse;
import com.gestion.application.exception.ContactNotFoundException;
import com.gestion.application.exception.GroupPhotosNotFoundException;
import com.gestion.application.model.GroupPhotos;
import com.gestion.application.model.Photo;
import com.gestion.application.repository.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class GroupPhotosServiceImpl {

  private final GroupPhotosRepository groupPhotosRepository;
  private final ContactRepository contactRepository;
  private final ConsultationRepository consultationRepository;
  private final RevisionRepository revisionRepository;
  private final PhotoRepository photoRepository;

  public GroupPhotos createGroupWithPhotos(CreateGroupPhotosRequest request, MultipartFile[] files)
      throws IOException {
    GroupPhotos group = new GroupPhotos();

    group.setTitle(request.getTitle());
    group.setDescription(request.getDescription());
    group.setCreationDate(LocalDate.now());

    group.setIsVisible(request.getIsVisible() != null ? request.getIsVisible() : Boolean.TRUE);

    group.setContact(
        contactRepository
            .findById(request.getContactId())
            .orElseThrow(() -> new RuntimeException("Contacto no encontrado")));

    if (request.getConsultationId() != null) {
      group.setConsultation(
          consultationRepository
              .findById(request.getConsultationId())
              .orElseThrow(() -> new RuntimeException("Consulta no encontrada")));
    }

    if (request.getRevisionId() != null) {
      group.setRevision(
          revisionRepository
              .findById(request.getRevisionId())
              .orElseThrow(() -> new RuntimeException("Revisión no encontrada")));
    }

    GroupPhotos savedGroup = groupPhotosRepository.save(group);

    Path folder =
        Paths.get(
            System.getProperty("user.dir"),
            "uploads",
            "photos",
            "group-" + savedGroup.getIdGroupPhotos());
    Files.createDirectories(folder);

    List<Photo> photos = new ArrayList<>();
    for (MultipartFile file : files) {
      String filename = UUID.randomUUID() + "-" + file.getOriginalFilename();
      Path path = folder.resolve(filename);
      file.transferTo(path.toFile());

      Photo photo = new Photo();
      photo.setGroupPhotos(savedGroup);
      photo.setFileRoute("photos/group-" + savedGroup.getIdGroupPhotos() + "/" + filename);
      photo.setIsVisible(true);
      photos.add(photo);
    }

    photoRepository.saveAll(photos);
    savedGroup.setPhotos(photos);

    return savedGroup;
  }

  public GroupPhotosResponse getGroupPhotos(Integer groupId) {
    GroupPhotos group =
        groupPhotosRepository
            .findById(groupId)
            .orElseThrow(() -> new GroupPhotosNotFoundException(groupId));

    GroupPhotosResponse response = new GroupPhotosResponse();
    response.setGroupId(group.getIdGroupPhotos());
    response.setTitle(group.getTitle());
    response.setDescription(group.getDescription());
    response.setCreationDate(group.getCreationDate());

    response.setContactId(group.getContact() != null ? group.getContact().getIdContact() : null);
    response.setConsultationId(
        group.getConsultation() != null ? group.getConsultation().getIdConsultation() : null);
    response.setRevisionId(
        group.getRevision() != null ? group.getRevision().getIdRevision() : null);

    String baseUrl = "http://localhost:8080/";
    List<String> photoUrls =
        group.getPhotos().stream().map(photo -> baseUrl + photo.getFileRoute()).toList();
    response.setPhotos(photoUrls);

    return response;
  }

  public List<GroupPhotosResponse> getAllGroupsByContact(Integer contactId) {

    if (!contactRepository.existsById(contactId)) {
      throw new ContactNotFoundException(contactId);
    }

    List<GroupPhotos> groups = groupPhotosRepository.findByContact_IdContact(contactId);
    String baseUrl = "http://localhost:8080/";

    return groups.stream()
        .map(
            group -> {
              GroupPhotosResponse response = new GroupPhotosResponse();
              response.setGroupId(group.getIdGroupPhotos());
              response.setTitle(group.getTitle());
              response.setDescription(group.getDescription());
              response.setCreationDate(group.getCreationDate());

              response.setContactId(
                  group.getContact() != null ? group.getContact().getIdContact() : null);
              response.setConsultationId(
                  group.getConsultation() != null
                      ? group.getConsultation().getIdConsultation()
                      : null);
              response.setRevisionId(
                  group.getRevision() != null ? group.getRevision().getIdRevision() : null);

              List<String> photoUrls =
                  group.getPhotos().stream().map(photo -> baseUrl + photo.getFileRoute()).toList();

              response.setPhotos(photoUrls);
              return response;
            })
        .toList();
  }

  public void deleteGroupPhotos(Integer groupId) throws IOException {
    GroupPhotos group =
        groupPhotosRepository
            .findById(groupId)
            .orElseThrow(() -> new GroupPhotosNotFoundException(groupId));

    // Eliminar fotos físicas del disco
    Path folder =
        Paths.get(System.getProperty("user.dir"), "uploads", "photos", "group-" + groupId);

    if (Files.exists(folder)) {
      Files.walk(folder).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
    }

    // Eliminar en cascada desde la base de datos
    groupPhotosRepository.delete(group);
  }
}
