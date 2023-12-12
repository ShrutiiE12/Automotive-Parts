package rideTech.backend.Service;

import java.util.List;
import rideTech.backend.Model.Order;

public interface OrderService {
	
	List<Order> getAllOrders();
	
   Order getOrderById(Long order_id);
   
   List<Order> getOrderByCustomerId(Long customer_id);

   Order createOrderItem(Order order, Long customer_id);

   Order createOrder(Order order,Long customer_id,Long cart_id);

    Order updateOrder(Order order,Long order_id);

    void deleteOrder(Long order_id);
    
	
	
}

