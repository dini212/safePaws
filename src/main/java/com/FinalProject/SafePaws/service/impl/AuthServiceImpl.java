package com.FinalProject.SafePaws.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.FinalProject.SafePaws.entity.Shelter;
import com.FinalProject.SafePaws.entity.User;
import com.FinalProject.SafePaws.repository.ShelterRepository;
import com.FinalProject.SafePaws.repository.UserRepository;
import com.FinalProject.SafePaws.security.JwtTokenProvider;
import com.FinalProject.SafePaws.service.AuthService;
import com.FinalProject.SafePaws.utils.dto.shelter.request.ShelterDTO;
import com.FinalProject.SafePaws.utils.dto.user.request.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShelterRepository shelterRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    public User registerCustomer(UserDTO request) {
        if (userRepository.existsByEmail(request.getEmail())){
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "Email already used");
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.saveAndFlush(request.toEntity());
    }

    @Override
    public Shelter registerShelter(ShelterDTO request) {
        if (shelterRepository.existsByEmail(request.getEmail())){
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "Email already used");
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return shelterRepository.saveAndFlush(request.toEntity());
    }

    @Override
    public String authenticateUser(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        try {
            return jwtTokenProvider.createToken(username, getAuthorisList(authentication));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "login failed", e.getCause());
        }
    }

    public List<String> getAuthorisList(Authentication authentication){
        return  authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return (UserDetails) authentication.getPrincipal();
    }
}
