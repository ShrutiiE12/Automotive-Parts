package rideTech.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rideTech.backend.Model.Order;
import rideTech.backend.Service.OrderService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{order_id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long order_id) {
        Order order = orderService.getOrderById(order_id);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/customer/{customer_id}")
    public List<Order> getOrderByCustomerId(@PathVariable Long customer_id) {
        return orderService.getOrderByCustomerId(customer_id);
    }

    //http://localhost:5555/api/orders/createOrder/2051/
    @PostMapping("/createOrder/{customer_id}")
    public ResponseEntity<Order> createOrderItems(@PathVariable Long customer_id, @RequestBody Order order) {
        return new ResponseEntity<>(orderService.createOrderItem(order, customer_id), HttpStatus.CREATED);
    }

    @PostMapping("{customer_id}/{cart_id}")
    public ResponseEntity<Order> CreatOrder(@PathVariable Long customer_id, @PathVariable Long cart_id, @RequestBody Order order) {
        return new ResponseEntity<>(orderService.createOrder(order, customer_id, cart_id), HttpStatus.CREATED);
    }

    @PutMapping("/{order_id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long order_id, @RequestBody Order order) {
        Order updatedOrder = orderService.updateOrder(order, order_id);
        if (updatedOrder != null) {
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{order_id}")
    public ResponseEntity<Void> deleteOrdersId(@PathVariable Long order_id) {
        orderService.deleteOrder(order_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
