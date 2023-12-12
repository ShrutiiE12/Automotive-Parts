package rideTech.backend.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rideTech.backend.Exception.ResourceNotFoundException;
import rideTech.backend.Model.Cart;
import rideTech.backend.Model.Customer;
import rideTech.backend.Model.Product;
import rideTech.backend.Repositories.CartRepository;
import rideTech.backend.Service.CartService;
import rideTech.backend.Service.CustomerService;
import rideTech.backend.Service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
	public ProductService productService;
	
	@Autowired
	public CustomerService customerService;
	

    @Override
    public Cart getCartById(Long cart_id) {
        return cartRepository.findById(cart_id).orElse(null);
    }
    
	
public CartServiceImpl(CartRepository cartRepository) {
		super();
		this.cartRepository = cartRepository;
	}


    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }
    
    @Override
    public Cart createCart(Cart cart, Long pid, Long customer_id) {

        Product product = productService.getProductByPid(pid);
        Customer customer = customerService.getCustomerById(customer_id);

        Cart existingCart = cartRepository.findByProductAndCustomer(product, customer);

        if (existingCart != null) {
            existingCart.setQuantity(existingCart.getQuantity() + cart.getQuantity());
            existingCart.setTotal_price(product.getPrice()* cart.getQuantity());
            return cartRepository.save(existingCart);
        } else {
            // If the cart item doesn't exist, create a new one
            cart.setProduct(product);
            cart.setTotal_price(product.getPrice());
            cart.setCustomer(customer);
            return cartRepository.save(cart);
        }
    }
/* 		cart.setProduct(product);
        cart.setTotal_price(product.getPrice());
        cart.setCustomer(customer);
        
        *
        *
        */

    @Override
    public Cart updateCartById(Long cart_id, Cart cart) {
        Cart existingCart = cartRepository.findById(cart_id).orElseThrow(()->new ResourceNotFoundException("Cart","Id",cart_id));
        existingCart.setQuantity(cart.getQuantity());
        existingCart.setTotal_price(cart.getTotal_price());
        existingCart.setId(cart.getId());
    	existingCart.setProduct(cart.getProduct());
        existingCart.setCustomer(cart.getCustomer());
        return existingCart;
    }

    @Override
    public void deleteCart(Long cart_id) {
    	Cart existingCart=cartRepository.findById(cart_id).orElseThrow(()->new ResourceNotFoundException("Cart","Id",cart_id));
    	Product product =productService.getProductByPid(existingCart.getProduct().getPid());
    	productService.updateProduct(product, product.getPid());
    	cartRepository.findById(cart_id).orElseThrow(()->new ResourceNotFoundException("Cart","Id",cart_id));
    	cartRepository.deleteById(cart_id);
    	
    }
    
    @Override
    public void deleteCartByCustomer(Customer custom_id) {
    	cartRepository.deleteCartByCustomer(custom_id);
    	
    }
    
}

