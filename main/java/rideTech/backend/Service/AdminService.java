package rideTech.backend.Service;

import java.util.List;

import rideTech.backend.Model.Admin;
import rideTech.backend.Model.Customer;
import rideTech.backend.Model.Product;

public interface AdminService {
	Admin saveAdmin(Admin admin);
	Admin loginAdmin(Admin admin);
	
	public List<Product> getAllProducts(Long adminId);
	public List<Customer> getAllCustomers(Long adminId);
}
