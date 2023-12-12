package rideTech.backend.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import rideTech.backend.Model.Admin;



public interface AdminRepository extends JpaRepository<Admin,Long> {
	
	Optional<Admin> findByAdminEmailIdAndAdminPassword(String emailId,String password);

	Optional<Admin> findByAdminEmailId(String emailId);
	
}

