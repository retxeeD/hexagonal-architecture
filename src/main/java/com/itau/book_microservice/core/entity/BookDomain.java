package com.itau.book_microservice.core.entity;

import java.util.UUID;

public class BookDomain {

    UUID id;
    String name;
    String author;
    Boolean availability;

    public BookDomain(UUID id, String name, String author, Boolean availability){
        this.id = id;
        this.name = name;
        this.author = author;
        this.availability = availability;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
}
