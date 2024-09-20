package com.itau.book_microservice.application.service;

import com.itau.book_microservice.core.entity.BookDomain;
import com.itau.book_microservice.core.repositories.BookRepository;
import com.itau.book_microservice.core.service.BookServiceDomain;
import com.itau.book_microservice.infra.exceptions.InvalidDataType;
import com.itau.book_microservice.infra.exceptions.NotFoundConsult;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class BookServiceApplication implements BookServiceDomain {

    private final BookRepository repository;

    public BookServiceApplication(BookRepository repository){
        this.repository = repository;
    }

    @Override
    public BookDomain register(BookDomain bookDomain) {
        return repository.register(bookDomain);
    }

    @Override
    public BookDomain rentAndReturn(UUID id) {
       repository.rentAndReturn(repository.findById(id).get().getId());
       return repository.findById(id).get();
    }

    @Override
    public BookDomain findByName(String name) {
        if (name.isEmpty() || name.isBlank()){
            throw new InvalidDataType("{\n\"name\": \"O pathParam é obrigatório.\"\n}");
        }
        return repository.findByName(name).orElseThrow(
                () -> new NotFoundConsult("{\n\"name\": \"O Livro '" + name + "' não foi encontrado.\"\n}")
        );
    }

    @Override
    public List<BookDomain> findByAuthor(String author) {
        if (author.isEmpty() || author.isBlank()){
            throw new InvalidDataType("{\n\"author\": \"O pathParam é obrigatório.\"\n}");
        }
        return repository.findByAuthor(author).orElseThrow(
                () -> new NotFoundConsult("{\n\"author\": \"Não existem livros do autor '" + author + "' cadastrados.\"\n}")
        );
    }

    @Override
    public void delete(UUID id) {
        repository.findById(id).orElseThrow(
                () -> new NotFoundConsult("{\n\"name\": \"O livro '" + id + "' não foi encontrado.\"\n}")
        );
        repository.delete(id);
    }

}