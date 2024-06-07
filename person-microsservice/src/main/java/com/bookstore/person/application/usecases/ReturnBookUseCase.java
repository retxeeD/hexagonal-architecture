package com.bookstore.person.application.usecases;

import com.bookstore.person.application.dto.PersonDto;
import com.bookstore.person.application.dto.RentBookDto;
import com.bookstore.person.core.domain.entities.PersonDomain;
import com.bookstore.person.core.domain.repositories.PersonRepository;

public class ReturnBookUseCase {

    private final PersonRepository repository;

    public ReturnBookUseCase(PersonRepository repository) {
        this.repository = repository;
    }

    public PersonDto execute (RentBookDto rentBookDto){
        PersonDomain person = repository.findByDocument(rentBookDto.getPersonDoc()).orElseThrow(() -> new RuntimeException("Person not found"));
        if (person.getRentBook() != rentBookDto.getRentBook()){
            throw new RuntimeException("This book ins't the right book that need to be returned, the correct book is " + person.getRentBook());
        }
        return new PersonDto(repository.rentBook(rentBookDto.getPersonDoc(), rentBookDto.getRentBook()).orElseThrow(() -> new RuntimeException("Something was wrong, try again.")));
    }
}
