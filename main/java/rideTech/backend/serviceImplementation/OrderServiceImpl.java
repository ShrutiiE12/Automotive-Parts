package rideTech.backend.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rideTech.backend.Exception.ResourceNotFoundException;
import rideTech.backend.Model.Cart;
import rideTech.backend.Model.Customer;
import rideTech.backend.Model.Order;
import rideTech.backend.Model.Product;
import rideTech.backend.Repositories.CartRepository;
import rideTech.backend.Repositories.OrderRepository;
import rideTech.backend.Service.CartService;
import rideTech.backend.Service.CustomerService;
import rideTech.backend.Service.OrderService;
import rideTech.backend.Service.ProductService;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    public ProductService productService;

    @Autowired
    public CartService cartService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartRepository custom;

    public OrderServiceImpl(OrderRepository orderRepository, ProductService productService, CartService cartService,
                            CustomerService customerService) {
        super();
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.customerService = customerService;
    }

    @Override
    public List<Order> getAllOrders() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Date date = new java.util.Date();
        String currentDate = sdf.format(date);
        String[] array = currentDate.split("/");
        int month = Integer.parseInt(array[0]);
        int day = Integer.parseInt(array[1]);
        int year = Integer.parseInt(array[2]);
        java.util.Date d = new java.util.Date(month, day, year);
        System.out.println(d);
        List<Order> orders = orderRepository.findAll();
        System.out.println(orders);
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long order_id) {
        return orderRepository.findById(order_id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", order_id));
    }

    @Override
    public List<Order> getOrderByCustomerId(Long customer_id) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Date date = new java.util.Date();
        String currentDate = sdf.format(date);
        String[] array = currentDate.split("/");
        int month = Integer.parseInt(array[0]);
        int day = Integer.parseInt(array[1]);
        int year = Integer.parseInt(array[2]);
        java.util.Date d = new java.util.Date(month, day, year);
        System.out.println(d);
        List<Order> order = orderRepository.findByCustomerCustomerId(customer_id);
        System.out.println(order);
        return orderRepository.findByCustomerCustomerId(customer_id);
    }

    @Override
    public Order createOrder(Order order, Long customer_id, Long cart_id) {
        Cart cart = cartService.getCartById(cart_id);
        Customer customer = customerService.getCustomerById(customer_id);
        order.setOrderStatus(order.getOrderStatus());
        order.setOrderPrice(order.getOrderPrice());
        order.setCreatedAt(LocalDateTime.now());
        
        order.setCustomer(customer);
        Order o = orderRepository.save(order);
        custom.deleteById(cart_id);
        return o;
    }

    @Override
    public Order updateOrder(Order order, Long order_id) {
        Optional<Order> existingOrders = orderRepository.findById(order_id);

        if (existingOrders.isPresent()) {
            Order updatedOrders = existingOrders.get();
            updatedOrders.setOrderStatus(order.getOrderStatus());
            updatedOrders.setOrderPrice(order.getOrderPrice());
            return orderRepository.save(updatedOrders);
        } else {
            return null; // or throw an exception
        }
    }

    @Override
    public void deleteOrder(Long order_id) {
        orderRepository.findById(order_id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", order_id));
        orderRepository.deleteById(order_id);
    }

    @Override
    public Order createOrderItem(Order order, Long customer_id) {
        Customer customer = customerService.getCustomerById(customer_id);
        order.setOrderStatus(order.getOrderStatus());
        order.setCreatedAt(LocalDateTime.now());
        order.setCustomer(customer);
        Order o = orderRepository.save(order);
        return o;
    }
}
