package rideTech.backend.Service;

import java.util.List;
import rideTech.backend.Model.Cart;
import rideTech.backend.Model.Customer;


public interface CartService {
    Cart getCartById(Long cart_id);

    List<Cart> getAllCarts();

    Cart createCart(Cart cart,Long pid,Long customer_id);

    Cart updateCartById(Long cart_id, Cart cart);
    
    void deleteCartByCustomer(Customer custom_id);
    
    void deleteCart(Long cart_id);
}

