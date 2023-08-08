package com.example.airlinereservation.data.repositories;

import com.example.airlinereservation.data.model.persons.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {

}
