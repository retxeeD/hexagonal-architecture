package hexagonal.microsservice.people.domain.dto;

import java.util.UUID;

public class PersonDomain {

    private UUID uuid;
    private String document;
    private String name;
    private Integer rentBook;

    public PersonDomain(UUID uuid, String document, String name, Integer rentBook) {
        this.uuid = uuid;
        this.document = document;
        this.name = name;
        this.rentBook = rentBook;
    }

    public PersonDomain(PersonDto personDto){
        this.document = personDto.getDocument();
        this.name = personDto.getName();
        this.rentBook = personDto.getRentBook();
    }

    public PersonDto PersonToPersonDto(){
        return new PersonDto(this.getDocument(), this.getName(), this.getRentBook());
    }

    public PersonDomain PersonDtoToPerson(PersonDto personDto){
        return new PersonDomain(personDto);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRentBook() {
        return rentBook;
    }

    public void setRentBook(Integer rentBook) {
        this.rentBook = rentBook;
    }
}
