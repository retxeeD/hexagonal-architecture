package com.bookstore.person.infrastructure.config;

import com.bookstore.person.application.services.PersonServiceImpl;
import com.bookstore.person.core.domain.repositories.PersonRepository;
import com.bookstore.person.core.domain.services.PersonServiceDomain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    PersonServiceDomain personServiceDomain (PersonRepository repository){
        return new PersonServiceImpl(repository);
    }
}
