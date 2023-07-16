package com.example.airlinereservation.data.repositories;
import com.example.airlinereservation.data.model.Passenger;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.airlinereservation.data.model.UserBiodata;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, String > {
	
	Optional<Passenger> findByUserBioData(UserBiodata bioData);
	
	@Transactional
	void deleteByUserBioData(UserBiodata userBiodata);
	
	boolean existsByUserBioData(UserBiodata foundBio);
}
