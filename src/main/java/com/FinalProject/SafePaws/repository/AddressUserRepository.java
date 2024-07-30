package com.FinalProject.SafePaws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FinalProject.SafePaws.entity.AddressUser;

import java.util.List;

@Repository
public interface AddressUserRepository extends JpaRepository<AddressUser, String> {
    List<AddressUser> findByUserId(String userId);
}
