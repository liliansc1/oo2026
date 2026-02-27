package ee.lilian.decathlon.repository;

import ee.lilian.decathlon.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
