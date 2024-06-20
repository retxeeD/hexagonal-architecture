package com.bookstore.person.application.usecases;

import com.bookstore.person.application.dto.PersonDto;
import com.bookstore.person.application.dto.RentBookDto;
import com.bookstore.person.core.domain.entities.PersonDomain;
import com.bookstore.person.core.domain.repositories.PersonRepository;
import org.springframework.stereotype.Component;

@Component
public class RentBookUseCase {
    private final PersonRepository repository;

    public RentBookUseCase(PersonRepository repository) {
        this.repository = repository;
    }

    public PersonDto execute (RentBookDto rentBookDto){
        PersonDomain person = repository.findByDocument(rentBookDto.getPersonDoc()).orElseThrow(() -> new RuntimeException("Person not found"));
        if (person.getRentBook() != null){
            throw new RuntimeException("This Person can't rent a book without return the book " + person.getRentBook() + " first.");
        }
        return new PersonDto(repository.rentBook(rentBookDto.getPersonDoc(), rentBookDto.getRentBook()).orElseThrow(() -> new RuntimeException("Something was wrong, try again.")));
    }
}
