package rideTech.backend.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import rideTech.backend.Model.Category;
import rideTech.backend.Model.Product;
import rideTech.backend.Service.ProductService;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{pid}")
    public ResponseEntity<Product> getProductByPid(@PathVariable Long pid) {
        Product product = productService.getProductByPid(pid);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
           
    @PostMapping("/addProduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

   // @Secured("ROLE_ADMIN")
    @PutMapping("/{pid}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long pid, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(product,pid);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{pid}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long pid) {
        productService.deleteProduct(pid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("products/{pid}")
	public ResponseEntity<Product> getProductById(@PathVariable("pid") Long pid) {
		return new ResponseEntity<Product>(productService.getProductByPid(pid), HttpStatus.OK);
	}
    
    @GetMapping("/category/{category_id}")
    public ResponseEntity<List<Product>> getAllProductsByCategory(@PathVariable("category_id") Long id) {
        List<Product> products = productService.getProductsByCategoryId(id);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }
   
}
