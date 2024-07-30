package com.FinalProject.SafePaws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FinalProject.SafePaws.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
