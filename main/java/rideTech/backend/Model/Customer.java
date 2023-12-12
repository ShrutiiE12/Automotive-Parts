package rideTech.backend.Model;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Customer {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sgen1")
    @SequenceGenerator(name = "sgen1", sequenceName = "s2", initialValue = 2000)
	@Column(name="customer_id")
	private Long customerId;
    
    @Column(name="user_name")
    private String username;
    
    private String firstname;
    
    private String lastname;
    
    @Column(name="email_id")
    @Email(message="Email  is not valid!")
	public String emailID;
    
    @Column(name="password")
	public String password;
    
    @Column(name="phone_number")
    private Long phone_number;
    
    @Column(name="address")
    private String address;
       
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    public Customer() {
   
    }
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", emailID=" + emailID + ", password=" + password + ", phone_number=" + phone_number + ", address="
				+ address + ", createdAt=" + createdAt + "]";
	}
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(Long phone_number) {
		this.phone_number = phone_number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public Customer(String username, String firstname, String lastname, String emailID, String password, Long phone_number,
			String address, LocalDateTime createdAt) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.emailID = emailID;
		this.password = password;
		this.phone_number = phone_number;
		this.address = address;
		this.createdAt = createdAt;
	}
       
}