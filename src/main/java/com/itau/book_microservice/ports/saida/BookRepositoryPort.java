package com.itau.book_microservice.ports.saida;

import com.itau.book_microservice.application.dto.BookEntity;
import com.itau.book_microservice.core.entity.BookDomain;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepositoryPort extends JpaRepository<BookEntity, UUID> {

    Optional<BookDomain> findByName(String name);
    Optional<List<BookDomain>> findByAuthor(String author);

    @Transactional
    @Modifying
    @Query("UPDATE BookEntity SET availability = ?2 WHERE id = ?1")
    Integer rentAndReturn(UUID id, Boolean flag);
}
