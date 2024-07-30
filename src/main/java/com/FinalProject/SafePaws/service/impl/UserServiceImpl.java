package com.FinalProject.SafePaws.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.FinalProject.SafePaws.entity.User;
import com.FinalProject.SafePaws.repository.UserRepository;
import com.FinalProject.SafePaws.service.AuthService;
import com.FinalProject.SafePaws.service.UserService;
import com.FinalProject.SafePaws.utils.dto.user.request.UpdateUserDTO;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AuthService authService;

    private PasswordEncoder passwordEncoder;

    @Override
    public User getByJWT(){

        UserDetails result = authService.getCurrentUser();

        return userRepository.findByEmail(result.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));
    }

    @Override
    public User updateUser(UpdateUserDTO request) {

        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));

        request.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setFullName(request.getFullName());
        user.setPhone(request.getPhone());
        user.setPassword(request.getPassword());

        return userRepository.saveAndFlush(user);
    }
}
