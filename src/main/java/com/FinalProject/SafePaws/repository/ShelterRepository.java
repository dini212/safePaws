package com.FinalProject.SafePaws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FinalProject.SafePaws.entity.Shelter;

import java.util.Optional;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, String> {

    boolean existsByEmail(String email);

    Optional<Shelter> findByEmail(String email);
}
