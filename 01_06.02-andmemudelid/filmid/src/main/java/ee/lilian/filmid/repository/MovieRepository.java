package ee.lilian.filmid.repository;

import ee.lilian.filmid.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long> {
}
