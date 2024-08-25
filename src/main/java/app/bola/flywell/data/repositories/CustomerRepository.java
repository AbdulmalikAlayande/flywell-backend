package app.bola.flywell.data.repositories;

import app.bola.flywell.data.model.persons.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import app.bola.flywell.data.model.persons.UserBioData;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String > {
	
	Optional<Customer> findByBioData(UserBioData bioData);
	
	@Transactional
	void deleteByBioData(UserBioData userBiodata);
	
	boolean existsByBioData(UserBioData foundBio);
	
}
