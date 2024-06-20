package com.bookstore.person.infrastructure.config;

import com.bookstore.person.adapters.inbound.rest.PersonController;
import com.bookstore.person.application.services.PersonServiceImpl;
import com.bookstore.person.application.usecases.RentBookUseCase;
import com.bookstore.person.application.usecases.ReturnBookUseCase;
import com.bookstore.person.core.domain.repositories.PersonRepository;
import com.bookstore.person.core.domain.services.PersonServiceDomain;
import com.bookstore.person.ports.inbound.rest.PersonControllerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    PersonServiceDomain personServiceDomain (PersonRepository repository){
        return new PersonServiceImpl(repository);
    }

/**    @Bean
    PersonControllerPort personController(RentBookUseCase rentBookUseCase, ReturnBookUseCase returnBookUseCase, PersonServiceImpl personService){
        return new PersonController(rentBookUseCase, returnBookUseCase, personService);
    }**/
}
