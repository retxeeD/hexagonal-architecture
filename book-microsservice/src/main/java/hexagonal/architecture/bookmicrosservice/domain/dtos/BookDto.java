package hexagonal.architecture.bookmicrosservice.domain.dtos;

import lombok.Getter;

@Getter
public class BookDto {

    private Integer number;
    private String name;

    public BookDto(Integer number, String name) {
        this.number = number;
        this.name = name;
    }

}
