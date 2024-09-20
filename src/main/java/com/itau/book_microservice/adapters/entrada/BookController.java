package com.itau.book_microservice.adapters.entrada;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.book_microservice.application.dto.BookDto;
import com.itau.book_microservice.application.service.BookServiceApplication;
import com.itau.book_microservice.core.entity.BookDomain;
import com.itau.book_microservice.ports.entrada.BookControllerPort;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/book")
public class BookController implements BookControllerPort {

    ObjectMapper objectMapper = new ObjectMapper();
    BookServiceApplication serviceApplication;

    public BookController(BookServiceApplication serviceApplication) {
        this.serviceApplication = serviceApplication;
    }

    @PostMapping("/register")
    public BookDto register(@Valid @RequestBody BookDto bookDto) throws JsonProcessingException {
        String bookDomain =  objectMapper.writeValueAsString(serviceApplication.register(bookDto.toBookDomain()));
        return objectMapper.readValue(bookDomain, BookDto.class);
    }

    @PutMapping("/rent-return/{id}")
    public BookDomain rentAndReturn(@PathVariable UUID id) throws JsonProcessingException {
        return serviceApplication.rentAndReturn(id);
    }

    @GetMapping("/find-by-name/{name}")
    public BookDomain findByName(@PathVariable String name) throws JsonProcessingException {
        return serviceApplication.findByName(name);
    }

    @GetMapping("/find-by-author/{author}")
    public List<BookDomain> findByAuthor(@PathVariable String author) throws JsonProcessingException {
        return serviceApplication.findByAuthor(author);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable UUID id) {
        serviceApplication.delete(id);
    }
}
