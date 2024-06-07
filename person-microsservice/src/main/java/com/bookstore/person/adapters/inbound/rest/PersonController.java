package com.bookstore.person.adapters.inbound.rest;

import com.bookstore.person.application.dto.PersonDto;
import com.bookstore.person.application.dto.RentBookDto;
import com.bookstore.person.application.usecases.*;
import com.bookstore.person.core.domain.services.PersonServiceDomain;
import com.bookstore.person.ports.inbound.rest.PersonControllerPort;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/person")
public class PersonController implements PersonControllerPort {

    private final RegisterPersonUseCase registerPersonUseCase;
    private final FindByDocumentUseCase findByDocumentUseCase;
    private final RentBookUseCase rentBookUseCase;
    private final ReturnBookUseCase returnBookUseCase;
    private final DeleteUseCase deleteUseCase;

    public PersonController(RegisterPersonUseCase registerPersonUseCase,
                            FindByDocumentUseCase findByDocumentUseCase,
                            RentBookUseCase rentBookUseCase,
                            ReturnBookUseCase returnBookUseCase,
                            DeleteUseCase deleteUseCase) {
        this.registerPersonUseCase = registerPersonUseCase;
        this.findByDocumentUseCase = findByDocumentUseCase;
        this.rentBookUseCase = rentBookUseCase;
        this.returnBookUseCase = returnBookUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping("/register")
    public PersonDto register(@RequestBody @Valid PersonDto personDto){
        return registerPersonUseCase.execute(personDto);
    }

    @PatchMapping("/rent-book")
    public PersonDto rentBook(@RequestBody @Valid RentBookDto rentBookDto) {
        return rentBookUseCase.execute(rentBookDto);
    }

    @PatchMapping("/return-book")
    public PersonDto returnBook(@RequestBody @Valid RentBookDto rentBookDto){
        return returnBookUseCase.execute(rentBookDto);
    }

    @GetMapping("/consult/{document}")
    public PersonDto findByDocument(@PathVariable Integer document){
        return findByDocumentUseCase.execute(document.toString());
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable UUID id){
        deleteUseCase.execute(id);
    }
}
