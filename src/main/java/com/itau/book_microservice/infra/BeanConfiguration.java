package com.itau.book_microservice.infra;

import com.itau.book_microservice.application.service.BookServiceApplication;
import com.itau.book_microservice.core.repositories.BookRepository;
import com.itau.book_microservice.core.service.BookServiceDomain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class BeanConfiguration {

    @Bean
    BookServiceDomain bookServiceDomain(BookRepository repository){
        return new BookServiceApplication(repository);
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
