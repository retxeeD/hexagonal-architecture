package hexagonal.microsservice.people.domain.ports.repositories;

import hexagonal.microsservice.people.domain.dto.PersonDomain;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepositoryPort {

    PersonDomain register(PersonDomain personDomain);

    Optional<PersonDomain> rentBook(String personDoc, Integer bookNumber);

    Optional<PersonDomain> returnBook(String personDoc, Integer bookNumber);

    Optional<PersonDomain> findByDocument(String document);

    Optional<PersonDomain> findById(UUID id);

    void delete(UUID personId);

}
