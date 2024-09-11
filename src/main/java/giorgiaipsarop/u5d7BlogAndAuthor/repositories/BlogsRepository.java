package giorgiaipsarop.u5d7BlogAndAuthor.repositories;

import giorgiaipsarop.u5d7BlogAndAuthor.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BlogsRepository extends JpaRepository<Blog, Integer> {
    Optional<Blog> findByTitle(String title);
    }

