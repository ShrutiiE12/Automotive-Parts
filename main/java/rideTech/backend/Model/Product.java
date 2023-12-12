package rideTech.backend.Model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name ="product_table")
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sgen3")
    @SequenceGenerator(name = "sgen3", sequenceName = "s4", initialValue = 5000)
	private Long pid;
    private String name;
    private Double price;
    private String description;
    private String brand;
    private String model;
	private String image;
    
    @ManyToOne( cascade=CascadeType.MERGE)
	@JoinColumn(name="category_id")
    private Category category;
            
    public Product() {
    	
    }

	public Long getId() {
		return pid;
	}

	public void setId(Long pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	
	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", name=" + name + ", price=" + price + ", description=" + description
				+ ", brand=" + brand + ", model=" + model + ", image=" + image + ", category=" + category + "]";
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}


	
	public Product(String name, Double price, String description, String brand, String model, Category category, String image) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.brand = brand;
		this.model = model;
		this.image = image;
		this.category = category;
		
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
}