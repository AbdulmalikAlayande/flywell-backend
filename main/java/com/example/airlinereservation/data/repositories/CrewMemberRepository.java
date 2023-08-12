package com.example.airlinereservation.data.repositories;

import com.example.airlinereservation.data.model.persons.CrewMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewMemberRepository extends JpaRepository<CrewMember, String> {
}
