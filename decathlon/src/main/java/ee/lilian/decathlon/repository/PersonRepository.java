package ee.lilian.decathlon.repository;
import ee.lilian.decathlon.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

//repository on andmebaasiga suhtlemiseks, lisamiseks, muutmiseks, kustutamiseks jne
public interface PersonRepository extends JpaRepository<Person,Long> {
    Page<Person> findByNationality(String nationality, Pageable pageable);//Leiab sportlased riigi järgi
}