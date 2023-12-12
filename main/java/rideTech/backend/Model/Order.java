package rideTech.backend.Model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name ="order_table")
public class Order {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sgen2")
    @SequenceGenerator(name = "sgen2", sequenceName = "s3", initialValue = 3000)
	@Column(name="order_id")
	private Long orderId;
 
    private String orderStatus; 
    
    private Long orderPrice;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    @Column 
    private String address;
    
    @ManyToOne( cascade=CascadeType.MERGE)
	@JoinColumn(name="customer_id")
    private Customer customer;
	
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinColumn(name="product_id")
    private List<Product> product;
     
    public Order() {
   
    }

	public Order(String orderStatus, LocalDateTime createdAt, String address, Customer customer,
			List<Product> product) {
		super();
		this.orderStatus = orderStatus;
		this.createdAt = createdAt;
		this.address = address;
		this.customer = customer;
		this.product = product;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderStatus=" + orderStatus + ", orderPrice=" + orderPrice
				+ ", createdAt=" + createdAt + ", address=" + address + ", customer=" + customer + ", product="
				+ product + "]";
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Long getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Long orderPrice) {
		this.orderPrice = orderPrice;
	}
    
}
