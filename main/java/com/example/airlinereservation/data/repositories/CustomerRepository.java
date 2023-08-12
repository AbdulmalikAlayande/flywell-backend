package com.example.airlinereservation.data.repositories;
import com.example.airlinereservation.data.model.Passenger;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.airlinereservation.data.model.persons.UserBioData;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Passenger, String > {
	
	Optional<Passenger> findByUserBioData(UserBioData bioData);
	
	@Transactional
	void deleteByUserBioData(UserBioData userBiodata);
	
	boolean existsByUserBioData(UserBioData foundBio);
	
}
