package hexagonal.microsservice.people.adapter.configuration;

import hexagonal.microsservice.people.domain.PersonServicePortImp;
import hexagonal.microsservice.people.adapter.client.RemoteServiceClient;
import hexagonal.microsservice.people.domain.ports.logger.PersonLogger;
import hexagonal.microsservice.people.domain.ports.repositories.PersonRepositoryPort;
import hexagonal.microsservice.people.domain.ports.services.PersonServicePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    PersonServicePort personServicePort(PersonRepositoryPort repository, PersonLogger logger, RemoteServiceClient feignClient){
        return new PersonServicePortImp(repository, logger, feignClient);
    }
}
