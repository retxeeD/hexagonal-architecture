package com.itau.book_microservice.adapters.saida;

import com.itau.book_microservice.application.dto.BookEntity;
import com.itau.book_microservice.core.entity.BookDomain;
import com.itau.book_microservice.core.repositories.BookRepository;
import com.itau.book_microservice.infra.exceptions.NotFoundConsult;
import com.itau.book_microservice.ports.saida.BookRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class BookRepositoryAdapter implements BookRepository {

    public final BookRepositoryPort repository;

    public BookRepositoryAdapter(BookRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public BookDomain register(BookDomain bookDomain) {
        return repository.save(
                new BookEntity(bookDomain.getName(), bookDomain.getAuthor(), bookDomain.getAvailability())
        ).toBookDomain();
    }

    @Override
    public Boolean rentAndReturn(UUID id) {
        return (repository.rentAndReturn(id, !findById(id).get().getAvailability()) != 0);
    }

    @Override
    public Optional<BookDomain> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Optional<List<BookDomain>> findByAuthor(String author) {
        return repository.findByAuthor(author);
    }

    @Override
    public Optional<BookDomain> findById(UUID id) {
        Optional<BookEntity> result = repository.findById(id);
        result.orElseThrow(
                () -> new NotFoundConsult("{\n\"id\":\"O livro " + id + " não foi encontrado.\"\n}")
        );
        return result.map(
                bookEntity -> new BookDomain(bookEntity.getId(), bookEntity.getName(), bookEntity.getAuthor(), bookEntity.getAvailability())
        );
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
