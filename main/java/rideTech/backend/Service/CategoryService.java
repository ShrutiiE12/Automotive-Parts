package rideTech.backend.Service;

import java.util.List;

import rideTech.backend.Model.Category;

public interface CategoryService {
	
	
    Category getCategoryById(Long id);

    List<Category> getAllCategories();

    Category createCategory(Category category);

    Category updateCategoryById(Long id, Category category);

    void deleteCategory(Long id);
    
    
}
