package rideTech.backend.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rideTech.backend.Model.Cart;
import rideTech.backend.Model.Customer;
import rideTech.backend.Model.Product;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	
	void deleteCartByCustomer(Customer custom);

	Cart findByProductAndCustomer(Product product, Customer customer);

}

