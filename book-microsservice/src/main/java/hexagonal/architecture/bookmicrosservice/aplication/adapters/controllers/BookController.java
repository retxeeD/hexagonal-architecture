package hexagonal.architecture.bookmicrosservice.aplication.adapters.controllers;

import hexagonal.architecture.bookmicrosservice.domain.Book;
import hexagonal.architecture.bookmicrosservice.domain.dtos.BookDto;
import hexagonal.architecture.bookmicrosservice.domain.ports.services.BookServicePort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/book")
public class BookController {

    private final BookServicePort bookServicePort;

    public BookController(BookServicePort bookServicePort) {
        this.bookServicePort = bookServicePort;
    }

    @PostMapping("/register")
    BookDto register(@RequestBody BookDto bookDto){
        return bookServicePort.register(bookDto).toDto();
    }

    @GetMapping("/consult")
    Optional<List<Book>> getAll(){
        return bookServicePort.getAll();
    }

    @GetMapping("/consult/{number}")
    Optional<BookDto> getByNumber(@PathVariable Integer number){
        return bookServicePort.getByNumber(number);
    }
}
