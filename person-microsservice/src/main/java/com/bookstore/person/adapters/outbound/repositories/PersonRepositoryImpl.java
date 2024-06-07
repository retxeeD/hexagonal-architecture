package com.bookstore.person.adapters.outbound.repositories;

import com.bookstore.person.application.dto.PersonEntity;
import com.bookstore.person.core.domain.entities.PersonDomain;
import com.bookstore.person.core.domain.repositories.PersonRepository;
import com.bookstore.person.ports.outbound.repositories.PersonRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class PersonRepositoryImpl implements PersonRepository {

    private final PersonRepositoryPort repository;

    public PersonRepositoryImpl(PersonRepositoryPort repositoryPort) {
        this.repository = repositoryPort;
    }

    @Override
    public PersonDomain register(PersonDomain personDomain) {
        PersonEntity person = new PersonEntity(personDomain.getDocument(),personDomain.getName(), personDomain.getRentBook());
        return repository.save(person).toPersonDomain();
    }

    @Override
    public Optional<PersonDomain> rentBook(String personDoc, Integer bookNumber) {
        repository.rentBook(personDoc, bookNumber);
        return repository.findByPersonDoc(personDoc).map(PersonEntity::toPersonDomain);
    }

    @Override
    public Optional<PersonDomain> returnBook(String personDoc, Integer bookNumber) {
        repository.rentBook(personDoc, bookNumber);
        return repository.findByPersonDoc(personDoc).map(PersonEntity::toPersonDomain);
    }

    @Override
    public Optional<PersonDomain> findByDocument(String document) {
        return repository.findByPersonDoc(document).map(PersonEntity::toPersonDomain);
    }

    @Override
    public Optional<PersonDomain> findById(UUID id) {
        return repository.findById(id).map(PersonEntity::toPersonDomain);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
