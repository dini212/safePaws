package com.FinalProject.SafePaws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FinalProject.SafePaws.entity.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, String> {
}
