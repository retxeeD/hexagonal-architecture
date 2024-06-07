package com.bookstore.person.core.domain.entities;

import java.util.UUID;

public class PersonDomain {

    private UUID id;
    private String document;
    private String name;
    private Integer rentBook;

    public PersonDomain() {
    }

    public PersonDomain(UUID id, String document, String name, Integer rentBook) {
        this.id = id;
        this.document = document;
        this.name = name;
        this.rentBook = rentBook;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRentBook() {
        return rentBook;
    }

    public void setRentBook(Integer rentBook) {
        this.rentBook = rentBook;
    }
}
