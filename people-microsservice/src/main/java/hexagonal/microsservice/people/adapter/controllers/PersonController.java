package hexagonal.microsservice.people.adapter.controllers;

import hexagonal.microsservice.people.domain.dto.PersonDomain;
import hexagonal.microsservice.people.adapter.dtos.PersonDto;
import hexagonal.microsservice.people.adapter.dtos.RentBookDto;
import hexagonal.microsservice.people.domain.exceptions.RentBookException;
import hexagonal.microsservice.people.domain.ports.services.PersonServicePort;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/person")
public class PersonController {

    private final PersonServicePort service;

    public PersonController(PersonServicePort service){
        this.service = service;
    }

    @PostMapping("/register")
    PersonDto register(@RequestBody @Valid PersonDto personDto){
        return service.register(personDto).PersonToPersonDto();
    }

    @PatchMapping("/rent-book")
    PersonDto rentBook(@RequestBody @Valid RentBookDto rentBookDto) throws RentBookException {
        return service.rentBook(rentBookDto)
                .map(PersonDomain::PersonToPersonDto).get();
    }

    @PatchMapping("/return-book")
    PersonDto returnBook(@RequestBody @Valid RentBookDto rentBookDto) throws RentBookException {
        return service.returnBook(rentBookDto)
                .map(PersonDomain::PersonToPersonDto).get();
    }

    @GetMapping("/consult/{document}")
    PersonDto findByDocument(@PathVariable Integer document){
        return service.findByDocument(document.toString()).map(PersonDomain::PersonToPersonDto).get();
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable UUID id){
        service.delete(id);
    }

}
