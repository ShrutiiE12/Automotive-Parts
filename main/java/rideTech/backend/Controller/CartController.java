
package rideTech.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import rideTech.backend.Model.Cart;
import rideTech.backend.Service.CartService;

import java.util.List;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("Cart/{cart_id}")
	public ResponseEntity<Cart> getCartById(@PathVariable("cart_id") Long cart_id) {
		return new ResponseEntity<Cart>(cartService.getCartById(cart_id), HttpStatus.OK);
	}
    
    @GetMapping("/list")
	public List<Cart> getAllCarts() {
		return cartService.getAllCarts();
	}

    @PostMapping("{customer_id}/{pid}")
	public ResponseEntity<Cart> createCart(@Valid @RequestBody Cart cart, @PathVariable Long pid,@PathVariable Long customer_id) {
		System.out.println("********");
		return new ResponseEntity<Cart>(cartService.createCart(cart, pid,customer_id), HttpStatus.CREATED);
	}

   // @Secured({"ROLE_ADMIN", "ROLE_CUSTOMER"})
    @PutMapping("/{cart_id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long cart_id, @RequestBody Cart cart) {
        Cart updatedCart = cartService.updateCartById(cart_id, cart);
        if (updatedCart != null) {
            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null);
        }
    }

   // @Secured({"ROLE_ADMIN", "ROLE_CUSTOMER"})
    @DeleteMapping("/{cart_id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long cart_id) {
    	System.out.println("hi i came here");
        cartService.deleteCart(cart_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

