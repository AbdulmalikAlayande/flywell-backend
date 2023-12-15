package com.example.airlinereservation.data.repositories;

import com.example.airlinereservation.data.model.persons.OTP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OTPRepository extends JpaRepository<OTP, String> {
	Optional<OTP> findByData(long data);
}
