package com.bookstore.person.core.domain.services;

import com.bookstore.person.application.dto.PersonDto;
import com.bookstore.person.application.dto.RentBookDto;
import com.bookstore.person.core.domain.entities.PersonDomain;

import java.util.UUID;

public interface PersonServiceDomain {

    PersonDto register(PersonDto person);

    PersonDto rentBook(RentBookDto rentBookDto);

    PersonDto returnBook(RentBookDto rentBookDto);

    PersonDto findByDocument(String document);

    PersonDto findById(UUID id);

    void delete(UUID personId);

}
