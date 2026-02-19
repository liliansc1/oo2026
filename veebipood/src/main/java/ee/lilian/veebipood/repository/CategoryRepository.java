package ee.lilian.veebipood.repository;

import ee.lilian.veebipood.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long>  {
}
