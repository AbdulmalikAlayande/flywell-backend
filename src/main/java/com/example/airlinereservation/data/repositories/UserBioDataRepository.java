package com.example.airlinereservation.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.airlinereservation.data.model.persons.UserBioData;

import java.util.Optional;

public interface UserBioDataRepository extends JpaRepository<UserBioData, String> {
	
	
	Optional<UserBioData> findByEmailAndPassword(String email, String password);
	
	boolean existsByEmail(String email);
	
	Optional<UserBioData> findByEmail(String userEmail);
}
