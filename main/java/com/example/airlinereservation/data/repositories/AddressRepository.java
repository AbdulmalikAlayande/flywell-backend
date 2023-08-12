package com.example.airlinereservation.data.repositories;

import com.example.airlinereservation.data.model.persons.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {

}
