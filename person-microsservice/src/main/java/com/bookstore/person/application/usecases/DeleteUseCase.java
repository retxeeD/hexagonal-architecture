package com.bookstore.person.application.usecases;

import com.bookstore.person.application.dto.PersonDto;
import com.bookstore.person.application.dto.PersonEntity;
import com.bookstore.person.core.domain.repositories.PersonRepository;

import java.util.UUID;

public class DeleteUseCase {
    private final PersonRepository repository;

    public DeleteUseCase(PersonRepository repository) {
        this.repository = repository;
    }

    public void execute (UUID id){
        repository.delete(id);
    }
}
