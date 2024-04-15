package hexagonal.architecture.bookmicrosservice.infrastructure.configuration;

import hexagonal.architecture.bookmicrosservice.domain.adapters.servicesImp.BookServiceImpl;
import hexagonal.architecture.bookmicrosservice.domain.ports.services.BookServicePort;
import hexagonal.architecture.bookmicrosservice.domain.ports.repositories.BookRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    BookServicePort bookServicePort(BookRepositoryPort bookRepositoryPort){
        return new BookServiceImpl(bookRepositoryPort);
    }
}
