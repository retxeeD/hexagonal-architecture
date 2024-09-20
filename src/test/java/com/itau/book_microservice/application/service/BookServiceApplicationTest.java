package com.itau.book_microservice.application.service;

import com.itau.book_microservice.adapters.saida.BookRepositoryAdapter;
import com.itau.book_microservice.application.dto.BookEntity;
import com.itau.book_microservice.core.entity.BookDomain;
import com.itau.book_microservice.ports.saida.BookRepositoryPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceApplicationTest {

    @Mock
    BookRepositoryPort repository;
    @InjectMocks
    BookRepositoryAdapter repositoryAdapter;
    @InjectMocks
    BookServiceApplication service;


    @Test
    void registerNewBook(){
        BookEntity entity = entityGenerate();
        BookDomain request = requestGenerate(entity.getId());
        when(repository.save(Mockito.any())).thenReturn(entity);
        ReflectionTestUtils.setField(repositoryAdapter, "repository", repository);
        ReflectionTestUtils.setField(service, "repository", repositoryAdapter);
        BookDomain response = service.register(request);
        Assertions.assertEquals(request.getName(), response.getName());
        Assertions.assertEquals(request.getAuthor(), response.getAuthor());
        Assertions.assertEquals(request.getAvailability(), response.getAvailability());
        Assertions.assertNotNull(response.getId());
    }

    @Test
    void rentReturnBook(){
        BookEntity entity = entityGenerate();
        when(repository.findById(Mockito.any())).thenReturn(Optional.of(entity));
        when(repository.rentAndReturn(Mockito.any(), Mockito.any())).thenReturn(1);
        ReflectionTestUtils.setField(service, "repository", repositoryAdapter);
        BookDomain response = service.rentAndReturn(entity.getId());
        Assertions.assertNotNull(response.getId());
        Assertions.assertNotNull(response.getName());
        Assertions.assertNotNull(response.getAuthor());
        Assertions.assertNotNull(response.getAvailability());
    }

    @Test
    void consultBoookByName(){
        Optional<BookDomain> entity = Optional.of(requestGenerate(UUID.randomUUID()));
        when(repository.findByName(Mockito.any())).thenReturn(entity);
        ReflectionTestUtils.setField(repositoryAdapter, "repository", repository);
        ReflectionTestUtils.setField(service, "repository", repositoryAdapter);
        BookDomain response = service.findByName("TestAuthor");
        Assertions.assertNotNull(response.getId());
        Assertions.assertNotNull(response.getName());
        Assertions.assertNotNull(response.getAuthor());
        Assertions.assertNotNull(response.getAvailability());
    }

    @Test
    void consultBookByAuthor(){
        Optional<List<BookDomain>> entity = Optional.of(List.of(requestGenerate(UUID.randomUUID()), requestGenerate(UUID.randomUUID())));
        when(repository.findByAuthor(Mockito.any())).thenReturn(entity);
        ReflectionTestUtils.setField(repositoryAdapter, "repository", repository);
        ReflectionTestUtils.setField(service, "repository", repositoryAdapter);
        List<BookDomain> response = service.findByAuthor("TestAuthor");
        Assertions.assertNotNull(response.getFirst().getId());
        Assertions.assertNotNull(response.getFirst().getName());
        Assertions.assertNotNull(response.getFirst().getAuthor());
        Assertions.assertNotNull(response.getFirst().getAvailability());
        Assertions.assertNotNull(response.getLast().getId());
        Assertions.assertNotNull(response.getLast().getName());
        Assertions.assertNotNull(response.getLast().getAuthor());
        Assertions.assertNotNull(response.getLast().getAvailability());
    }

    @Test
    void deleteBook(){
        BookEntity entity = entityGenerate();
        when(repository.findById(Mockito.any())).thenReturn(Optional.of(entity));
        ReflectionTestUtils.setField(service, "repository", repositoryAdapter);
        service.delete(entity.getId());
    }

    private BookDomain requestGenerate(UUID id){
        return new BookDomain(id,"TestBook", "TestAuthor", true);
    }

    private BookEntity entityGenerate(){
        return new BookEntity(UUID.randomUUID(), "TestBook", "TestAuthor", true);
    }

}
