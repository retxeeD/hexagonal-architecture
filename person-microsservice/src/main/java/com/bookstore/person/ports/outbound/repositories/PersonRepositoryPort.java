package com.bookstore.person.ports.outbound.repositories;

import com.bookstore.person.application.dto.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepositoryPort extends JpaRepository<PersonEntity, UUID> {

    Optional<PersonEntity> findByPersonDoc (String personDoc);

    @Transactional
    @Modifying
    @Query("UPDATE PersonEntity p SET p.rentBook = ?2 WHERE p.personDoc = ?1")
    int rentBook(String document, Integer book);

}
