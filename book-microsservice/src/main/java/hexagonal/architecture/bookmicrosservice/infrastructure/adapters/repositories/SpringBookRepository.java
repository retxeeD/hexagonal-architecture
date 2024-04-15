package hexagonal.architecture.bookmicrosservice.infrastructure.adapters.repositories;

import hexagonal.architecture.bookmicrosservice.infrastructure.adapters.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringBookRepository extends JpaRepository<BookEntity, UUID> {

    Optional<BookEntity> findByNumber(Integer number);
}
