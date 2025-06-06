package com.gestion.application.repository;

import com.gestion.application.model.Protocol;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProtocolRepository extends JpaRepository<Protocol, Integer> {

  Optional<Protocol> findByContact_IdContact(Integer contactId);
}
