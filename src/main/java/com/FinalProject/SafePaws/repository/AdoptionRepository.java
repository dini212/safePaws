package com.FinalProject.SafePaws.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.FinalProject.SafePaws.entity.Adoption;
import com.FinalProject.SafePaws.utils.enums.AdoptionStatus;

@Repository
public interface AdoptionRepository extends JpaRepository<Adoption, String>, JpaSpecificationExecutor<Adoption> {
    
    Optional<Adoption> findFirstByOrderByInspectionDateDesc();

    List<Adoption> findByAnimalIdAndAdoptionStatus(String id, AdoptionStatus status);
}
