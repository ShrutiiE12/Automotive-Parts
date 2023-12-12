package rideTech.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


import rideTech.backend.Model.Customer;
import rideTech.backend.Service.CustomerService;
import java.util.List;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    
    public CustomerController(CustomerService customerService) { super();
	  this.customerService = customerService; }
	     

    @GetMapping("customer/{customer_id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("customer_id") Long customer_id) {
		return new ResponseEntity<Customer>(customerService.getCustomerById(customer_id), HttpStatus.OK);
	}

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customer = customerService.getAllCustomer();
        return ResponseEntity.ok(customer);
    }

    @PutMapping("customer/{customer_id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("customer_id") Long customer_id, @RequestBody Customer customer) {
	   Customer updatedCustomer = customerService.updateCustomer(customer,customer_id);
        if (updatedCustomer != null) {
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

  //  @Secured("ROLE_ADMIN")
    @DeleteMapping("customer/{customer_id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customer_id) {
        customerService.deleteCustomer(customer_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PostMapping("/register")
	public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody Customer customer) {

		return new ResponseEntity<Customer>(customerService.createCustomer(customer), HttpStatus.CREATED);
	}
    
    @PostMapping("/login")
	public ResponseEntity<Customer> loginCustomer(@RequestBody Customer customer) {

		return new ResponseEntity<Customer>(customerService.loginCustomer(customer), HttpStatus.OK);

	}
    @PostMapping("/forgotpassword")
	public Customer getCustomerByEmail(@RequestBody Customer customer) {
		return customerService.getCustomerByEmail(customer);
	}
    @PostMapping("/{customid}/{newpassword}")
	public Customer changeCustomerPassword(@PathVariable("customid") Long customid,@PathVariable("newpassword") String newpassword) {
		Customer custom=customerService.getCustomerById(customid);
		custom.setPassword(newpassword);
		customerService.updateCustomer(custom, customid);
		return custom;
	}
}
