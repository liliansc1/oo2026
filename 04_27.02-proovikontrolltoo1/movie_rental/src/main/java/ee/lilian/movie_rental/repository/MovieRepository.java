package ee.lilian.movie_rental.repository;

import ee.lilian.movie_rental.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//repository on andmebaasiga suhtlemiseks, lisamiseks, muutmiseks, kustutamiseks jne
public interface MovieRepository extends JpaRepository<Movie,Long> {
    // SELECT * FROM film WHERE days =
    List<Movie> findByDays(Integer days);
}

