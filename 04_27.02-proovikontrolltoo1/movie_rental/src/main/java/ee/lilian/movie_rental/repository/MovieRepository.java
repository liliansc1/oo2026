package ee.lilian.movie_rental.repository;

import ee.lilian.movie_rental.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

//repository on andmebaasiga suhtlemiseks, lisamiseks, muutmiseks, kustutamiseks jne
public interface MovieRepository extends JpaRepository<Movie,Long> {
    Long id(Long id);
}

