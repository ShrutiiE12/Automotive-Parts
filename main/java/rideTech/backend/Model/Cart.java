package rideTech.backend.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Cart {
    
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sgen")
    @SequenceGenerator(name = "sgen", sequenceName = "s1", initialValue = 1000)
    @Column(name = "cart_id")
    private Long id;
    
    @Column(name = "quantity")
	private Long quantity;
    
    @Column(name = "total_price")
	private Double total_price;
    
    @ManyToOne( cascade=CascadeType.MERGE)
	@JoinColumn(name="product_id")
    private Product product;
	
	@ManyToOne( cascade=CascadeType.MERGE)
	@JoinColumn(name="customer_id")
    private Customer customer;    
    
 // Constructors
    public Cart() {
    }
	
	public Cart(Long quantity, Double total_price, Product product, Customer customer) {
	super();
	this.quantity = quantity;
	this.total_price = total_price;
	this.product = product;
	this.customer = customer;
	
}
	
	
	@Override
	public String toString() {
		return "Cart [id=" + id + ", quantity=" + quantity + ", total_price=" + total_price + ", product=" + product
				+ ", customer=" + customer + "]";
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Double getTotal_price() {
		return total_price;
	}

	
}

