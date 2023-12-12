package rideTech.backend.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rideTech.backend.Exception.ResourceNotFoundException;
import rideTech.backend.Model.Cart;
import rideTech.backend.Model.Category;
import rideTech.backend.Model.Product;
import rideTech.backend.Repositories.CategoryRepository;
import rideTech.backend.Repositories.ProductRepository;
import rideTech.backend.Service.ProductService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;


    // Other autowired dependencies...

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
       
    @Override
    public Product getProductByPid(Long pid) {
        return productRepository.findById(pid).orElseThrow(()->new ResourceNotFoundException("Product","Id",pid));
    }

    @Override
    public Product createProduct(Product product) {
        product.setName(product.getName());
		product.setModel(product.getModel());
		product.setPrice(product.getPrice());
		product.setDescription(product.getDescription());
		product.setBrand(product.getBrand());
		product.setImage(product.getImage());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product,Long pid) {
        Product existingProduct = productRepository.findById(pid).orElse(null);

        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setModel(product.getModel());
            existingProduct.setBrand(product.getBrand());
            existingProduct.setImage(product.getImage());
            existingProduct.setCategory(product.getCategory());
            return productRepository.save(existingProduct);
        } else {
            // Handle the case where the product with the given ID is not found
            return null;
        }
    }

    @Override
    public void deleteProduct(Long pid) {
        productRepository.deleteById(pid);
    }
    
    @Override
    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }
    
    @Override
    public List<Product> getProductsByCategoryId(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);

        if (category != null) {
            return productRepository.findByCategory(category);
        } else {
            return Collections.emptyList(); // or throw an exception, or handle it as needed
        }
    }
    
    
    @Override
    public List<Product> findByCategory(Category category) {
		return productRepository.findByCategory(category);
		}
}
