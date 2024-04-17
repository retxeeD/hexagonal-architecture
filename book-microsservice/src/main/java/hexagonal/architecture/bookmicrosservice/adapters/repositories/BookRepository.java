package hexagonal.architecture.bookmicrosservice.adapters.repositories;

import hexagonal.architecture.bookmicrosservice.adapters.entities.BookEntity;
import hexagonal.architecture.bookmicrosservice.domain.Book;
import hexagonal.architecture.bookmicrosservice.domain.ports.repositories.BookRepositoryPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BookRepository implements BookRepositoryPort {

    private final SpringBookRepository repository;

    public BookRepository(SpringBookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<List<Book>> getAll() {
        List<BookEntity> returnList= repository.findAll();
        return Optional.of(returnList.stream().map(BookEntity::toBook).collect(Collectors.toList()));
    }

    @Override
    public Optional<Book> getByNumber(Integer number) {
        Optional<BookEntity> bookEntity = repository.findByNumber(number);
        return bookEntity.map(book ->
                new Book(book.toBook().getId(), book.toBook().getNumber(), book.toBook().getName()));
    }

    @Override
    public Optional<Book> getById(UUID id) {
        Optional<BookEntity> bookEntity = repository.findById(id);
        return bookEntity.map(book ->
            new Book(book.toBook().getId(), book.toBook().getNumber(), book.toBook().getName()));
    }

    @Transactional
    @Override
    public Book register(Book book) {
        BookEntity bookEntity = new BookEntity(book);
        return repository.save(bookEntity).toBook();
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
