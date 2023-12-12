package rideTech.backend.Service;

import rideTech.backend.Model.Category;
import rideTech.backend.Model.Product;
import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
        
    Product getProductByPid(Long pid);

    Product createProduct(Product product);

    Product updateProduct(Product product,Long pid);
    
    List<Product> findByCategory(Category category);

    void deleteProduct(Long Pid);
    
    List<Product> getProductsByCategory(Category category);
    
    List<Product> getProductsByCategoryId(Long categor_id);
}
