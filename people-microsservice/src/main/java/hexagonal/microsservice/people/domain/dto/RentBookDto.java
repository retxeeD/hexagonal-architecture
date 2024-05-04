package hexagonal.microsservice.people.domain.dto;

public class RentBookDto {

    private String personDoc;
    private Integer rentBook;

    public RentBookDto(String personDoc, Integer rentBook) {
        this.personDoc = personDoc;
        this.rentBook = rentBook;
    }

    public String getPersonDoc() {
        return personDoc;
    }

    public Integer getRentBook() {
        return rentBook;
    }
}
