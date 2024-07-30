package com.FinalProject.SafePaws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FinalProject.SafePaws.entity.AddressShelter;

import java.util.List;

@Repository
public interface AddressShelterRepository extends JpaRepository<AddressShelter, String> {
    List<AddressShelter> findByShelterId(String shelterId);
}
