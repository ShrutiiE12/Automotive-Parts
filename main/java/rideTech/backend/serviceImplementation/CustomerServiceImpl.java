package rideTech.backend.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rideTech.backend.Exception.ResourceNotFoundException;
import rideTech.backend.Model.Customer;
import rideTech.backend.Model.Order;
import rideTech.backend.Model.Product;
import rideTech.backend.Repositories.CustomerRepository;
import rideTech.backend.Service.CustomerService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	} 
    
    @Override
    public Customer getCustomerById(Long customer_id) {
        return customerRepository.findById(customer_id).orElseThrow(()->new ResourceNotFoundException("Customer","Id",customer_id));
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }
   
    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
	public Customer loginCustomer(Customer customer) {
		
	return this.customerRepository.findByEmailIDAndPassword(customer.emailID,customer.password).orElseThrow(()->new ResourceNotFoundException("Customer ", "Id",customer.emailID+" and password "+customer.password ));
    }
    public Customer getCustomerByEmail(Customer customer)
	{
		return this.customerRepository.findByEmailID(customer.emailID).orElseThrow(()->new ResourceNotFoundException("Customer ", "Email",customer.emailID ));
	}
    
    @Override
    public Customer updateCustomer(Customer customer, Long customer_id) {
       Customer existingCustomer = customerRepository.findById(customer_id).orElseThrow(()->new ResourceNotFoundException("Customer","Id",customer_id));
       existingCustomer.setUsername(customer.getUsername());
       existingCustomer.setFirstname(customer.getFirstname());
       existingCustomer.setLastname(customer.getLastname());
       existingCustomer.setEmailID(customer.getEmailID());
       existingCustomer.setPassword(customer.getPassword());
       existingCustomer.setPhone_number(customer.getPhone_number());
       existingCustomer.setAddress(customer.getAddress());
            return customerRepository.save(existingCustomer);
        
    }

    @Override
    public void deleteCustomer(Long customer_id) {
    	customerRepository.findById(customer_id).orElseThrow(()->new ResourceNotFoundException("Customer","Id",customer_id));
        customerRepository.deleteById(customer_id);
    }



	
}
