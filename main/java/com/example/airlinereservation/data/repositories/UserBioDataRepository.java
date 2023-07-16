package com.example.airlinereservation.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.airlinereservation.data.model.UserBiodata;

import java.util.Optional;

public interface UserBioDataRepository extends JpaRepository<UserBiodata, String> {
	
	void deleteByUserName(String userName);
	Optional<UserBiodata> findByUserName(String userName);
}
