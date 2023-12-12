package rideTech.backend.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rideTech.backend.Model.Category;
import rideTech.backend.Repositories.CategoryRepository;
import rideTech.backend.Service.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) {
    	category.setName(category.getName());
    	category.setCatimage(category.getCatimage());
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategoryById(Long id, Category category) {
        Optional<Category> existingCategory = categoryRepository.findById(id);

        if (existingCategory.isPresent()) {
            Category updatedCategory = existingCategory.get();
            updatedCategory.setName(category.getName());
            updatedCategory.setCatimage(category.getCatimage());
            return categoryRepository.save(updatedCategory);
        } else {
            return null; // or throw an exception
        }
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
