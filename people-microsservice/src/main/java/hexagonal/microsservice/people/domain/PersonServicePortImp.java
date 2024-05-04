package hexagonal.microsservice.people.domain;

import feign.FeignException;
import hexagonal.microsservice.people.domain.client.RemoteServiceClient;
import hexagonal.microsservice.people.domain.dto.PersonDomain;
import hexagonal.microsservice.people.domain.dto.PersonDto;
import hexagonal.microsservice.people.domain.dto.RentBookDto;
import hexagonal.microsservice.people.domain.exceptions.RentBookException;
import hexagonal.microsservice.people.domain.ports.repositories.PersonRepositoryPort;
import hexagonal.microsservice.people.domain.ports.services.PersonServicePort;
import org.springframework.http.HttpStatus;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class PersonServicePortImp implements PersonServicePort {

    private final PersonRepositoryPort repository;

    private RemoteServiceClient feignClient;

    public PersonServicePortImp(PersonRepositoryPort repository, RemoteServiceClient feignClient) {
        this.repository = repository;
        this.feignClient = feignClient;
    }

    @Override
    public PersonDomain register(PersonDto person) {
        return repository.register(new PersonDomain(person));
    }

    @Override
    public Optional<PersonDomain> rentBook(RentBookDto rentBookDto) throws RentBookException {
        PersonDomain person = findByDocument(rentBookDto.getPersonDoc()).get();
        if (person.getRentBook() != null){
            throw new RuntimeException("A pessoa '"+ rentBookDto.getPersonDoc() +"' nao pode alugar outor livro pois ja possui o livro '"+ person.getRentBook() +"' registrado.");
        }
        try {
            feignClient.getBook( rentBookDto.getRentBook());
        }catch (FeignException ex){
            throw new RentBookException(ex.contentUTF8(), HttpStatus.NOT_FOUND);
        }
        if (repository.rentBook(rentBookDto.getPersonDoc(), rentBookDto.getRentBook()).isPresent()){
            throw new RentBookException("Ocorreu um erro ao alugar o livro, tente novamente.", HttpStatus.BAD_GATEWAY);
        }
        return findByDocument(rentBookDto.getPersonDoc());
    }

    @Override
    public Optional<PersonDomain> returnBook(RentBookDto rentBookDto) throws RentBookException {
        PersonDomain person = findByDocument(rentBookDto.getPersonDoc()).get();
        if (!Objects.equals(person.getRentBook(), rentBookDto.getRentBook())){
            throw new RuntimeException("Este não é o livro que deve ser devolvido, o livro alugado por este usuário foi " + person.getRentBook());
        }
        try {
            feignClient.getBook(rentBookDto.getRentBook());
        }catch (FeignException ex){
            throw new RentBookException(ex.contentUTF8(), HttpStatus.NOT_FOUND);
        }
        if (repository.returnBook(rentBookDto.getPersonDoc(), rentBookDto.getRentBook()).isEmpty()){
            throw new RentBookException("Ocorreu um erro ao alugar o livro, tente novamente.", HttpStatus.BAD_GATEWAY);
        }
        return findByDocument(rentBookDto.getPersonDoc());
    }

    @Override
    public Optional<PersonDomain> findByDocument(String document) {
        return Optional.ofNullable(repository.findByDocument(document)
                .orElseThrow(
                        () -> new RuntimeException("Person not found, check the document passed.")
                ));
    }

    @Override
    public Optional<PersonDomain> findById(UUID id) {
        return Optional.ofNullable(repository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Person not found, check the document passed.")
                ));
    }

    @Override
    public void delete(UUID personId) {
        findById(personId);
        repository.delete(personId);
    }
}
