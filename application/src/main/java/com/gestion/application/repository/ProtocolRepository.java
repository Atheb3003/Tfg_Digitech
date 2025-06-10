package com.gestion.application.repository;

import com.gestion.application.model.Protocol;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProtocolRepository extends JpaRepository<Protocol, Integer> {

  List<Protocol> findAllByContact_IdContact(Integer contactId);
}
