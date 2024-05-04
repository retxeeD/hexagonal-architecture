package hexagonal.microsservice.people.adapter.repositories;

import hexagonal.microsservice.people.adapter.entities.PersonEntity;
import hexagonal.microsservice.people.domain.dto.PersonDomain;
import hexagonal.microsservice.people.domain.ports.repositories.PersonRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class PersonRepositoryImpl implements PersonRepositoryPort {

    private final SpringPersonRepository repository;

    public PersonRepositoryImpl(SpringPersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public PersonDomain register(PersonDomain personDomain) {
        PersonEntity person = new PersonEntity(personDomain.getUuid(), personDomain.getDocument(),personDomain.getName(), personDomain.getRentBook());
        return repository.save(person).toPerson();
    }

    @Override
    public Optional<PersonDomain> rentBook(String personDoc, Integer bookNumber) {
        repository.rentBook(personDoc, bookNumber);
        return repository.findByPersonDoc(personDoc).map(PersonEntity::toPerson);
    }

    @Override
    public Optional<PersonDomain> returnBook(String personDoc, Integer bookNumber) {
        repository.rentBook(personDoc, bookNumber);
        return repository.findByPersonDoc(personDoc).map(PersonEntity::toPerson);
    }

    @Override
    public Optional<PersonDomain> findByDocument(String document) {
        return repository.findByPersonDoc(document).map(PersonEntity::toPerson);
    }

    @Override
    public Optional<PersonDomain> findById(UUID id) {
        return repository.findById(id).map(PersonEntity::toPerson);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
