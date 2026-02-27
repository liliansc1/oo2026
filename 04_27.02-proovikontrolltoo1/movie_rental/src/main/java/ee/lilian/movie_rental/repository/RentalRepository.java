package ee.lilian.movie_rental.repository;

import ee.lilian.movie_rental.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental,Long> {
}
