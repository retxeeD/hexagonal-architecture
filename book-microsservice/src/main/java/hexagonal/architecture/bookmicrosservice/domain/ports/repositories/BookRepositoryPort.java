package hexagonal.architecture.bookmicrosservice.domain.ports.repositories;

import hexagonal.architecture.bookmicrosservice.domain.Book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepositoryPort {

    Optional<List<Book>> getAll();

    Optional<Book> getByNumber(Integer number);

    Optional<Book> getById(UUID id);

    Book register(Book book);

    void delete(UUID id);
}
