package com.itau.book_microservice.adapters.entrada;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.book_microservice.adapters.saida.BookRepositoryAdapter;
import com.itau.book_microservice.application.dto.BookDto;
import com.itau.book_microservice.application.service.BookServiceApplication;
import com.itau.book_microservice.core.repositories.BookRepository;
import com.itau.book_microservice.ports.saida.BookRepositoryPort;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    BookServiceApplication service;
    @Mock
    BookRepositoryPort repository;

    @Mock
    BookRepositoryAdapter bookRepository;
    @InjectMocks
    BookServiceApplication bookServiceApplication;

    ObjectMapper objectMapper = new ObjectMapper();


    /** ------------------------------------------------- register ------------------------------------------------- **/

    @Test
    void validarRegisterNameComValorEmBranco() throws Exception {
        BookDto request = new BookDto("     ", "TestAuthor", true);
        Map<String, String> errors = new HashMap<>();
        errors.put("name", "O campo 'name' é obrigatório.");
        ResponseEntity<Map<String, String>> erroreturn = new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/book/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsBytes(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(erroreturn.getBody())));
    }

    @Test
    void validarRegisterNameComValorVazio() throws Exception {
        BookDto request = new BookDto("", "TestAuthor", true);
        Map<String, String> errors = new HashMap<>();
        errors.put("name", "O campo 'name' é obrigatório.");
        ResponseEntity<Map<String, String>> erroreturn = new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/book/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsBytes(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(erroreturn.getBody())));
    }

    @Test
    void validarRegisterAuthorComValorEmBranco() throws Exception {
        BookDto request = new BookDto("TesteName", "    ", true);
        Map<String, String> errors = new HashMap<>();
        errors.put("author", "O campo 'author' é obrigatório.");
        ResponseEntity<Map<String, String>> erroreturn = new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/book/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsBytes(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(erroreturn.getBody())));
    }

    @Test
    void validarRegisterAuthorComValorVazio() throws Exception {
        BookDto request = new BookDto("TesteName", "", true);
        Map<String, String> errors = new HashMap<>();
        errors.put("author", "O campo 'author' é obrigatório.");
        ResponseEntity<Map<String, String>> erroreturn = new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/book/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsBytes(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(erroreturn.getBody())));
    }

    @Test
    void validarRegisterAvailabilityComValorInvalido() throws Exception {
        BookDto request = new BookDto("TesteName", "TesteAuthor", null);
        Map<String, String> errors = new HashMap<>();
        errors.put("availability", "O campo 'availability' é obrigatório.");
        ResponseEntity<Map<String, String>> erroreturn = new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/book/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsBytes(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(erroreturn.getBody())));
    }

/** ------------------------------------------------- rent-return -------------------------------------------------- **/

    @Test
    void validarRentReturnComIdInvalido() throws Exception {
        Map<String, String> errors = new HashMap<>();
        errors.put("id", "Valor inválido 'TEXTO'. Tipo esperado: UUID.");
        ResponseEntity<Map<String, String>> erroReturn = new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        this.mockMvc.perform(MockMvcRequestBuilders.put("/v1/book/rent-return/{id}", "TEXTO")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(erroReturn.getBody())));
    }

    @Test
    void validarRentReturnComIdInexistente() throws Exception {
        UUID id = UUID.randomUUID();
        Map<String, String> errors = new HashMap<>();
        errors.put("id", "O livro " + id + " não foi encontrado.");
        ResponseEntity<Map<String, String>> erroReturn = new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);


        when(repository.findById(Mockito.any())).thenReturn(Optional.empty());
        ReflectionTestUtils.setField(bookServiceApplication, "repository", bookRepository);


        this.mockMvc.perform(MockMvcRequestBuilders.put("/v1/book/rent-return/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(erroReturn.getBody())));
    }

}