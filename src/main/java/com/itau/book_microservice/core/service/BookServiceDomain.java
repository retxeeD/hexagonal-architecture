package com.itau.book_microservice.core.service;

import com.itau.book_microservice.core.entity.BookDomain;

import java.util.List;
import java.util.UUID;

public interface BookServiceDomain {

    BookDomain register(BookDomain bookDomain);
    BookDomain rentAndReturn(UUID id);
    BookDomain findByName(String name);
    List<BookDomain> findByAuthor(String author);
    void delete(UUID id);
}
