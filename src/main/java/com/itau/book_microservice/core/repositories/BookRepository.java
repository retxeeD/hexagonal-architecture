package com.itau.book_microservice.core.repositories;

import com.itau.book_microservice.core.entity.BookDomain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository {

    BookDomain register(BookDomain bookDomain);
    Boolean rentAndReturn(UUID id);
    Optional<BookDomain> findByName(String name);
    Optional<List<BookDomain>> findByAuthor(String author);
    Optional<BookDomain> findById(UUID id);
    void delete(UUID id);
}
