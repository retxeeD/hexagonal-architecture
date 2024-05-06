package hexagonal.microsservice.people.domain.ports.services;

import hexagonal.microsservice.people.domain.dto.PersonDomain;
import hexagonal.microsservice.people.adapter.dtos.PersonDto;
import hexagonal.microsservice.people.adapter.dtos.RentBookDto;
import hexagonal.microsservice.people.domain.exceptions.RentBookException;

import java.util.Optional;
import java.util.UUID;

public interface PersonServicePort {

    PersonDomain register(PersonDto person);

    Optional<PersonDomain> rentBook(RentBookDto rentBookDto) throws RentBookException;

    Optional<PersonDomain> returnBook(RentBookDto rentBookDto) throws RentBookException;

    Optional<PersonDomain> findByDocument(String document);

    Optional<PersonDomain> findById(UUID id);

    void delete(UUID personId);
}
