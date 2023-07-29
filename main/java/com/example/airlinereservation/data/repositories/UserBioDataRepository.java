package com.example.airlinereservation.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.airlinereservation.data.model.UserBioData;

import java.util.Optional;

public interface UserBioDataRepository extends JpaRepository<UserBioData, String> {
	
	void deleteByUserName(String userName);
	Optional<UserBioData> findByUserName(String userName);
	
	Optional<UserBioData> findByEmailAndPassword(String email, String password);
}
