package hexagonal.microsservice.people.adapter;

import hexagonal.microsservice.people.domain.dto.PersonDomain;
import hexagonal.microsservice.people.domain.dto.PersonDto;
import hexagonal.microsservice.people.domain.dto.RentBookDto;
import hexagonal.microsservice.people.domain.exceptions.RentBookException;
import hexagonal.microsservice.people.domain.ports.services.PersonServicePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/person")
public class PersonController {

    private final PersonServicePort service;

    public PersonController(PersonServicePort service){
        this.service = service;
    }

    @PostMapping("/")
    PersonDto register(@RequestBody PersonDto personDto){
        return service.register(personDto).PersonToPersonDto();
    }

    @PatchMapping("/rent-book")
    PersonDto rentBook(@RequestBody RentBookDto rentBookDto) throws RentBookException {
        return service.rentBook(rentBookDto)
                .map(PersonDomain::PersonToPersonDto).get();
    }

    @PatchMapping("/return-book")
    PersonDto returnBook(@RequestBody RentBookDto rentBookDto) throws RentBookException {
        return service.returnBook(rentBookDto)
                .map(PersonDomain::PersonToPersonDto).get();
    }

    @GetMapping("/consult/{document}")
    PersonDto findByDocument(@PathVariable String document){
        return service.findByDocument(document).map(PersonDomain::PersonToPersonDto).get();
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable UUID id){
        service.delete(id);
    }

}
