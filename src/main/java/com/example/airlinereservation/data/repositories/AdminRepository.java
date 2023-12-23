package com.example.airlinereservation.data.repositories;

import com.example.airlinereservation.data.model.persons.Admin;
import com.example.airlinereservation.data.model.persons.UserBioData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, String> {
	
	Optional<Admin> findByBioData(UserBioData bioData);
	
	Optional<Admin> findByEmail(String email);
}
