package com.bookstore.person.application.dto;

import com.bookstore.person.core.domain.entities.PersonDomain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "person")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String personDoc;

    @Column(nullable = false)
    private String name;

    @Column()
    private Integer rentBook;

    public PersonEntity() {
    }

    public PersonEntity(String personDoc, String name, Integer rentBook) {
        this.personDoc = personDoc;
        this.name = name;
        this.rentBook = rentBook;
    }

    public PersonEntity(PersonDto personDto){
        this.personDoc = personDto.getDocument();
        this.name = personDto.getName();
        this.rentBook = personDto.getRentBook();
    }

    public PersonDomain toPersonDomain(){
        return new PersonDomain(this.id, this.personDoc,this.name,  this.rentBook);
    }

    public PersonDto toPersonDto(){
        return new PersonDto(this.personDoc, this.name, this.rentBook);
    }

}