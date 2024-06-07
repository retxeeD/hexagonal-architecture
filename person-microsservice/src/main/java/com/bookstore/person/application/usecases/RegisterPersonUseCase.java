package com.bookstore.person.application.usecases;

import com.bookstore.person.application.dto.PersonDto;
import com.bookstore.person.application.dto.PersonEntity;
import com.bookstore.person.core.domain.entities.PersonDomain;
import com.bookstore.person.core.domain.repositories.PersonRepository;

public class RegisterPersonUseCase {
    private final PersonRepository repository;

    public RegisterPersonUseCase(PersonRepository repository) {
        this.repository = repository;
    }

    public PersonDto execute (PersonDto personDto){
        PersonEntity personEntity = new PersonEntity(personDto);
        return new PersonDto(repository.register(personEntity.toPersonDomain()));
    }

}
