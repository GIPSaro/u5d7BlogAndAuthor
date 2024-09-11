package giorgiaipsarop.u5d7BlogAndAuthor.repositories;

import giorgiaipsarop.u5d7BlogAndAuthor.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findByEmail(String email);

}