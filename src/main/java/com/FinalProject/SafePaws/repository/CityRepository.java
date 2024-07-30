package com.FinalProject.SafePaws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FinalProject.SafePaws.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City,String> {
}
