package ee.lilian.veebipood.repository;

import ee.lilian.veebipood.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
    Person findByEmail(String email);
}
