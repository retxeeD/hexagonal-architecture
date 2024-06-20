package com.bookstore.person.adapters.inbound.rest;

import com.bookstore.person.application.dto.PersonDto;
import com.bookstore.person.application.dto.RentBookDto;
import com.bookstore.person.application.services.PersonServiceImpl;
import com.bookstore.person.application.usecases.*;
import com.bookstore.person.ports.inbound.rest.PersonControllerPort;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/person")
public class PersonController implements PersonControllerPort {

    private final PersonServiceImpl service;
    private final RentBookUseCase rentBookUseCase;
    private final ReturnBookUseCase returnBookUseCase;


    public PersonController(RentBookUseCase rentBookUseCase,
                            ReturnBookUseCase returnBookUseCase,
                            PersonServiceImpl service) {
        this.service = service;
        this.rentBookUseCase = rentBookUseCase;
        this.returnBookUseCase = returnBookUseCase;
    }

    @PostMapping("/register")
    public PersonDto register(@RequestBody @Valid PersonDto personDto){
        return service.register(personDto);
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
        return service.findByDocument(document.toString());
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable UUID id){
        service.delete(id);
    }
}
