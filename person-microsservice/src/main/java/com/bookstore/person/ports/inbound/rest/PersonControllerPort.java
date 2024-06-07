package com.bookstore.person.ports.inbound.rest;

import com.bookstore.person.application.dto.PersonDto;
import com.bookstore.person.application.dto.RentBookDto;

import java.util.UUID;

public interface PersonControllerPort {

    PersonDto register(PersonDto personDto);

    PersonDto rentBook(RentBookDto rentBookDto);

    PersonDto returnBook(RentBookDto rentBookDto);

    PersonDto findByDocument(Integer document);

    void delete(UUID id);
}
