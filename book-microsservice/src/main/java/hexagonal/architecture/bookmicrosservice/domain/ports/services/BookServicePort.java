package hexagonal.architecture.bookmicrosservice.domain.ports.services;

import hexagonal.architecture.bookmicrosservice.domain.Book;
import hexagonal.architecture.bookmicrosservice.domain.dtos.BookDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookServicePort {

    Optional<List<Book>> getAll();

    Optional<BookDto> getByNumber(Integer number);

    Optional<Book> getById(UUID id);

    Book register(BookDto book);

    void delete(UUID id);

}
