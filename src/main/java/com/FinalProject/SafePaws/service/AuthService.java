package com.FinalProject.SafePaws.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.FinalProject.SafePaws.entity.Shelter;
import com.FinalProject.SafePaws.entity.User;
import com.FinalProject.SafePaws.utils.dto.shelter.request.ShelterDTO;
import com.FinalProject.SafePaws.utils.dto.user.request.UserDTO;

public interface AuthService {

    User registerCustomer(UserDTO request);

    Shelter registerShelter(ShelterDTO request);

    String authenticateUser(String username, String password);

    UserDetails getCurrentUser();

}
