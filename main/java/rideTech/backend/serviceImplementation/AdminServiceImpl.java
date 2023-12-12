package rideTech.backend.serviceImplementation;

import java.util.List;
//import java.util.NoSuchElementException;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rideTech.backend.Exception.ResourceNotFoundException;
import rideTech.backend.Model.Admin;
import rideTech.backend.Model.Customer;
import rideTech.backend.Model.Product;
import rideTech.backend.Repositories.AdminRepository;
import rideTech.backend.Repositories.CustomerRepository;
import rideTech.backend.Repositories.ProductRepository;
import rideTech.backend.Service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
private AdminRepository adminRepository;

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ProductRepository productRepository;

	public AdminServiceImpl(AdminRepository adminRepository) {
		super();
		this.adminRepository = adminRepository;
	}

	@Override
	public Admin saveAdmin(Admin admin) {
		System.out.println("admin register service"+admin);

		return adminRepository.save(admin);
	}

	@Override
	public Admin loginAdmin(Admin admin) {
	    Admin existingAdmin = adminRepository.findByAdminEmailId(admin.getAdminEmailId())
	            .orElseThrow(() -> new ResourceNotFoundException("Admin", "Email", admin.getAdminEmailId()));

	    if (existingAdmin.getAdminPassword().equals(admin.getAdminPassword())) {
	        return existingAdmin;
	    } else {
	        throw new ResourceNotFoundException("Admin", "Password", "Incorrect password");
	    }
	}

	@Override
	public List<Product> getAllProducts(Long adminId) {
		return  productRepository.findAll();
	}
	
	@Override
	public List<Customer> getAllCustomers(Long adminId) {
		return customerRepository.findAll();
	}

			
		

		
}