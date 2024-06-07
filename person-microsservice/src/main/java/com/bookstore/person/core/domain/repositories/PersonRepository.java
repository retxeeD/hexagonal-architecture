package com.bookstore.person.core.domain.repositories;

import com.bookstore.person.core.domain.entities.PersonDomain;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository {

    PersonDomain register(PersonDomain person);

    Optional<PersonDomain> rentBook(String personDoc, Integer bookNumber);

    Optional<PersonDomain> returnBook(String personDoc, Integer bookNumber);

    Optional<PersonDomain> findByDocument(String document);

    Optional<PersonDomain> findById(UUID id);

    void delete(UUID personId);
}
