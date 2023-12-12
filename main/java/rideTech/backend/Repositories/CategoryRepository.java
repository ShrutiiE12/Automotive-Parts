package rideTech.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rideTech.backend.Model.Category;
import rideTech.backend.Model.Product;  

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findById(Category category_id);  
	
	

}
