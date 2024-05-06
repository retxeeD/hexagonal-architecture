package hexagonal.microsservice.people.domain;

import feign.FeignException;
import hexagonal.microsservice.people.domain.client.RemoteServiceClient;
import hexagonal.microsservice.people.domain.dto.PersonDomain;
import hexagonal.microsservice.people.domain.dto.PersonDto;
import hexagonal.microsservice.people.domain.dto.RentBookDto;
import hexagonal.microsservice.people.domain.exceptions.NotFoundException;
import hexagonal.microsservice.people.domain.exceptions.RentBookException;
import hexagonal.microsservice.people.domain.ports.logger.PersonLogger;
import hexagonal.microsservice.people.domain.ports.repositories.PersonRepositoryPort;
import hexagonal.microsservice.people.domain.ports.services.PersonServicePort;
import org.springframework.http.HttpStatus;

import java.util.*;

public class PersonServicePortImp implements PersonServicePort {

    private final PersonRepositoryPort repository;

    private final PersonLogger logger;

    private RemoteServiceClient feignClient;

    public PersonServicePortImp(PersonRepositoryPort repository, PersonLogger logger,RemoteServiceClient feignClient) {
        this.repository = repository;
        this.logger = logger;
        this.feignClient = feignClient;
    }

    @Override
    public PersonDomain register(PersonDto person) {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("Request", person);

        PersonDomain response = repository.register(new PersonDomain(person));
        hashMap.put("Response", response);

        logger.info(hashMap);
        return response;
    }

    @Override
    public Optional<PersonDomain> rentBook(RentBookDto rentBookDto) throws RentBookException {

        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("Request", rentBookDto);

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
        Optional<PersonDomain> response = findByDocument(rentBookDto.getPersonDoc());

        hashMap.put("Response", response.get());

        logger.info(hashMap);

        return response;

    }

    @Override
    public Optional<PersonDomain> returnBook(RentBookDto rentBookDto) throws RentBookException {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("Request", rentBookDto);

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
            throw new RentBookException("Ocorreu um erro ao devolver o livro, tente novamente.", HttpStatus.BAD_GATEWAY);
        }
        Optional<PersonDomain> response = findByDocument(rentBookDto.getPersonDoc());

        hashMap.put("Response", response.get());

        logger.info(hashMap);

        return response;
    }

    @Override
    public Optional<PersonDomain> findByDocument(String document) {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("Document", document);

        try{
            Optional<PersonDomain> response = repository.findByDocument(document);

            hashMap.put("Response", response);
            logger.info(hashMap);

            return Optional.of(response.orElseThrow(
                    () -> new NotFoundException("Person not found, check the document passed.", HttpStatus.NOT_FOUND)
            ));
        }
        catch (Exception e){
            throw new RuntimeException("FindByDocument method error.\b Error message: " + e.getMessage() + "\b Error cause: " + e.getCause());
        }
    }

    @Override
    public Optional<PersonDomain> findById(UUID id) {

        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("ID", id);

        try {
            Optional<PersonDomain> response = repository.findById(id);

            hashMap.put("Response", response);
            logger.info(hashMap);

            return Optional.of(response.orElseThrow(
                    () -> new NotFoundException("Person not found, check the document passed.", HttpStatus.NOT_FOUND)
            ));
        }catch (Exception e){
            throw new RuntimeException("FindById method error.\b Error message: " + e.getMessage() + "\b Error cause: " + e.getCause());
        }
    }

    @Override
    public void delete(UUID personId) {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("ID", personId);

        findById(personId);
        repository.delete(personId);
        hashMap.put("Message", "Object Deleted");
    }
}
