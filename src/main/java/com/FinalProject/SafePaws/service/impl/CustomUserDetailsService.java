package com.FinalProject.SafePaws.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.FinalProject.SafePaws.entity.Shelter;
import com.FinalProject.SafePaws.entity.User;
import com.FinalProject.SafePaws.repository.ShelterRepository;
import com.FinalProject.SafePaws.repository.UserRepository;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShelterRepository shelterRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }

        Optional<Shelter> shelterOptional = shelterRepository.findByEmail(email);
        if (shelterOptional.isPresent()) {
            return shelterOptional.get();
        }

        throw new UsernameNotFoundException("User with email " + email + " not found");
    }
}