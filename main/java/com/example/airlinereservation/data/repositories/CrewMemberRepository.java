package com.example.airlinereservation.data.repositories;

import com.example.airlinereservation.data.model.persons.CrewMember;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CrewMemberRepository extends JpaRepository<CrewMember, String> {
	boolean existsByUserName(String userName);

    Optional<CrewMember> findByUserName(String userName);

    @Transactional
    void deleteByUserName(String userName);
}
