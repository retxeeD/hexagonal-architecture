package com.bookstore.person.application.services;

import com.bookstore.person.application.dto.PersonDto;
import com.bookstore.person.application.dto.RentBookDto;
import com.bookstore.person.core.domain.repositories.PersonRepository;
import com.bookstore.person.core.domain.services.PersonServiceDomain;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class PersonServiceImpl implements PersonServiceDomain {

    private final PersonRepository repository;

    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    public PersonDto register(PersonDto person) {
        return new PersonDto(repository.register(person.toDomain()));
    }

    public PersonDto rentBook(RentBookDto rentBookDto) {
        return new PersonDto(repository.rentBook(rentBookDto.getPersonDoc(), rentBookDto.getRentBook()).get());
    }

    public PersonDto returnBook(RentBookDto rentBookDto) {
        return new PersonDto(repository.returnBook(rentBookDto.getPersonDoc(), rentBookDto.getRentBook()).get());
    }

    public PersonDto findByDocument(String document) {
        return new PersonDto(repository.findByDocument(document).get());
    }

    public PersonDto findById(UUID id) {
        return new PersonDto(repository.findById(id).get());
    }

    public void delete(UUID id) {
        repository.delete(id);
    }
}
