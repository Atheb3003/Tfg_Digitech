package com.gestion.application.repository;

import com.gestion.application.model.GroupPhotos;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupPhotosRepository extends JpaRepository<GroupPhotos, Integer> {

  List<GroupPhotos> findByContact_IdContact(Integer contactId);
}
