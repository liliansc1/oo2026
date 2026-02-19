package ee.lilian.veebipood.repository;

import ee.lilian.veebipood.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


//repository on andmebaasiga suhtlemiseks, lisamiseks, muutmiseks, kustutamiseks jne
public interface ProductRepository extends JpaRepository<Product,Long> {
}
