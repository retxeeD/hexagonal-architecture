package hexagonal.architecture.bookmicrosservice.domain;

import hexagonal.architecture.bookmicrosservice.domain.dtos.BookDto;

import java.util.UUID;

public class Book {

    private UUID id;
    private Integer number;
    private String name;

    public Book(UUID id, Integer number, String name) {
        this.id = id;
        this.number = number;
        this.name = name;
    }

    public Book(BookDto bookDto){
        this.number = bookDto.getNumber();
        this.name = bookDto.getName();
    }

    public UUID getId() {
        return id;
    }

    public BookDto toDto(Book book){
       return new BookDto(book.getNumber(), book.getName());
    }

    public BookDto toDto(){
        return new BookDto(this.getNumber(), this.getName());
    }

    public Integer getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
