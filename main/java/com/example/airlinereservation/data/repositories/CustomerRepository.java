package com.example.airlinereservation.data.repositories;
import com.example.airlinereservation.data.model.persons.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.airlinereservation.data.model.persons.UserBioData;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String > {
	
	Optional<Customer> findByBioData(UserBioData bioData);
	
	@Transactional
	void deleteByBioData(UserBioData userBiodata);
	
	boolean existsByBioData(UserBioData foundBio);
	
}
