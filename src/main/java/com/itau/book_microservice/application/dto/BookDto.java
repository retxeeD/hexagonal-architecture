package com.itau.book_microservice.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.itau.book_microservice.core.entity.BookDomain;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true)
public class BookDto {

    @NotBlank(message = "O campo 'name' é obrigatório.")
    String name;

    @NotBlank(message = "O campo 'author' é obrigatório.")
    String author;

    @NotNull(message = "O campo 'availability' é obrigatório.")
    Boolean availability;

    public BookDomain toBookDomain(){
        return new BookDomain(null, this.name, this.author, this.availability);
    }

}