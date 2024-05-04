package hexagonal.microsservice.people.domain.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "book-microsservice", url = "localhost:8081")
public interface RemoteServiceClient {

    @GetMapping("/v1/book/consult/{id}")
    String getBook(@PathVariable Integer id);
}
