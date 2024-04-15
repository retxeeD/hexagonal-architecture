package hexagonal.architecture.bookmicrosservice.infrastructure.adapters.entities;

import hexagonal.architecture.bookmicrosservice.domain.Book;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true)
    private Integer number;
    @Column(nullable = false)
    private String name;


    public BookEntity() {
    }

    public BookEntity(Book book) {
        this.id = book.getId();
        this.number = book.getNumber();
        this.name = book.getName();
    }

    public Book toBook() {
        return new Book(this.id, this.number, this.name);
    }
}

