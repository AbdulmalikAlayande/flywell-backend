package com.example.airlinereservation.data.repositories;

import com.example.airlinereservation.data.model.persons.OTP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OTPRepository extends JpaRepository<OTP, String> {
}
