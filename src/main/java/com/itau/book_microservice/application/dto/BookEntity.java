package com.itau.book_microservice.application.dto;

import com.itau.book_microservice.core.entity.BookDomain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity @Getter @AllArgsConstructor @NoArgsConstructor
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    @Column(unique = true, nullable = false)
    String name;
    @Column(nullable = false)
    String author;
    @Column(nullable = false)
    Boolean availability;

    public BookEntity(String name, String author, Boolean availability){
        this.name = name;
        this.author = author;
        this.availability = availability;
    }

    public BookDomain toBookDomain(){
        return new BookDomain(this.id, this.name, this.author, this.availability);
    }
}
