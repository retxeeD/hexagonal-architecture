package com.bookstore.person.application.usecases;

import com.bookstore.person.application.dto.PersonDto;
import com.bookstore.person.application.dto.PersonEntity;
import com.bookstore.person.core.domain.repositories.PersonRepository;

import java.util.UUID;

public class FindByDocumentUseCase {

    private final PersonRepository repository;

    public FindByDocumentUseCase(PersonRepository repository) {
        this.repository = repository;
    }

    public PersonDto execute (String document){
        return new PersonDto(repository.findByDocument(document).orElseThrow(() -> new RuntimeException("Person not found")));
    }

}
