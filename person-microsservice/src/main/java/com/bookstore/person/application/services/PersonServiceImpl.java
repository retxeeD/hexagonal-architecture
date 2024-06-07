package com.bookstore.person.application.services;

import com.bookstore.person.application.dto.PersonDto;
import com.bookstore.person.application.dto.RentBookDto;
import com.bookstore.person.core.domain.repositories.PersonRepository;
import com.bookstore.person.core.domain.services.PersonServiceDomain;

import java.util.UUID;

public class PersonServiceImpl implements PersonServiceDomain {

    private final PersonRepository repository;

    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    public PersonDto register(PersonDto person) {
        return null;
    }

    public PersonDto rentBook(RentBookDto rentBookDto) {
        return null;
    }

    public PersonDto returnBook(RentBookDto rentBookDto) {
        return null;
    }

    public PersonDto findByDocument(String document) {
        return null;
    }

    public PersonDto findById(UUID id) {
        return null;
    }

    public void delete(UUID personId) {

    }
}
