package hexagonal.microsservice.people.adapter.entities;

import hexagonal.microsservice.people.domain.dto.PersonDomain;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
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

    public PersonEntity(UUID id, String personDoc, String name, Integer rentBook) {
        this.id = id;
        this.personDoc = personDoc;
        this.name = name;
        this.rentBook = rentBook;
    }

    public PersonDomain toPerson(){
        return new PersonDomain(this.id, this.personDoc,this.name,  this.rentBook);
    }
}
