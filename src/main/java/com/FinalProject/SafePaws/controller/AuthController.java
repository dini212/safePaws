package com.FinalProject.SafePaws.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.FinalProject.SafePaws.entity.Shelter;
import com.FinalProject.SafePaws.entity.User;
import com.FinalProject.SafePaws.service.AuthService;
import com.FinalProject.SafePaws.service.RestClientAddressService;
import com.FinalProject.SafePaws.utils.dto.Res;
import com.FinalProject.SafePaws.utils.dto.security.request.AuthRequest;
import com.FinalProject.SafePaws.utils.dto.security.response.AuthResponse;
import com.FinalProject.SafePaws.utils.dto.security.response.RegisterResponse;
import com.FinalProject.SafePaws.utils.dto.shelter.request.ShelterDTO;
import com.FinalProject.SafePaws.utils.dto.user.request.UserDTO;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final RestClientAddressService restClientAddressService;

    @PostMapping("/register-user")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody UserDTO userDTO) {

        User user = authService.registerCustomer(userDTO);

        return Res.renderJson(RegisterResponse.fromUser(user), "User registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/register-shelter")
    public ResponseEntity<?> registerShelter(@Valid @RequestBody ShelterDTO shelterDTO) {

        Shelter shelter = authService.registerShelter(shelterDTO);

        return Res.renderJson(RegisterResponse.fromShelter(shelter), "Shelter registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticatecustomer(@Valid @RequestBody AuthRequest authRequest) {
        String token = authService.authenticateUser(authRequest.getEmail(), authRequest.getPassword());

        AuthResponse result = new AuthResponse(token);

        return Res.renderJson(result, "Login successfully", HttpStatus.ACCEPTED);
    }

//    @GetMapping("/me")
//    public ResponseEntity<?> getCurrentUser() {
//        UserDetails result = authService.getCurrentUser();
//        return Res.renderJson(result, "OK", HttpStatus.ACCEPTED);
//    }
}
