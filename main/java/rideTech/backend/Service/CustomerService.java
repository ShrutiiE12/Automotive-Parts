package rideTech.backend.Service;

import java.util.List;

import rideTech.backend.Model.Customer;

public interface CustomerService {
	
    Customer getCustomerById(Long customer_id);

    List<Customer> getAllCustomer();

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer,Long customer_id);
	
    void deleteCustomer(Long customer_id);

    Customer getCustomerByEmail(Customer customer);
	
	Customer loginCustomer(Customer customer);
	
	
	
}

