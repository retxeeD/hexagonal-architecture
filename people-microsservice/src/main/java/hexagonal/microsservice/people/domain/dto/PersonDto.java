package hexagonal.microsservice.people.domain.dto;

public class PersonDto {

    private String document;
    private String name;
    private Integer rentBook;

    public PersonDto(String document, String name, Integer rentBook) {
        this.document = document;
        this.name = name;
        this.rentBook = rentBook;
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
