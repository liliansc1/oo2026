package ee.lilian.autod.repository;

import ee.lilian.autod.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Long> {
    Long id(Long id);
}

