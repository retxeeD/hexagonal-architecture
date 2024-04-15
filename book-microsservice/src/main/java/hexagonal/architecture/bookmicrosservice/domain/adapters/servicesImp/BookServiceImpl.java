package hexagonal.architecture.bookmicrosservice.domain.adapters.servicesImp;

import hexagonal.architecture.bookmicrosservice.domain.Book;
import hexagonal.architecture.bookmicrosservice.domain.dtos.BookDto;
import hexagonal.architecture.bookmicrosservice.domain.ports.services.BookServicePort;
import hexagonal.architecture.bookmicrosservice.domain.ports.repositories.BookRepositoryPort;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BookServiceImpl implements BookServicePort {

    private final BookRepositoryPort repository;

    public BookServiceImpl(BookRepositoryPort repository){
        this.repository = repository;
    }

    @Override
    public Optional<List<Book>> getAll() {
        return Optional.ofNullable(repository.getAll()
                .orElseThrow(
                        () -> new EntityNotFoundException("There aren't books to list.")
                ));
    }

    @Override
    public Optional<BookDto> getByNumber(Integer number) {
        Optional<Book> bookReturned = Optional.ofNullable(repository.getByNumber(number)
                .orElseThrow(
                        () -> new EntityNotFoundException("The book '"+ number +"' wasn't found.")
                ));
        Book book = bookReturned.orElseThrow();
        return Optional.ofNullable(book.toDto());
    }

    @Override
    public Optional<Book> getById(UUID id) {

        return Optional.ofNullable(repository.getById(id)
                .orElseThrow(
                    () -> new EntityNotFoundException("The book '"+ id +"' wasn't found.")
                ));
    }

    @Override
    public Book register(BookDto bookDto) {
        Book book = new Book(bookDto);
        return repository.register(book);
    }

    @Override
    public void delete(UUID id) {
        getById(id);
        repository.delete(id);
    }
}
