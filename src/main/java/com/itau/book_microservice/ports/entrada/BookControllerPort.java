package com.itau.book_microservice.ports.entrada;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.book_microservice.application.dto.BookDto;
import com.itau.book_microservice.core.entity.BookDomain;

import java.util.List;
import java.util.UUID;

public interface BookControllerPort {

    BookDto register(BookDto bookDto) throws JsonProcessingException;
    BookDomain rentAndReturn(UUID id) throws JsonProcessingException;
    BookDomain findByName(String name) throws JsonProcessingException;
    List<BookDomain> findByAuthor(String author) throws JsonProcessingException;
    void delete(UUID id);

}
