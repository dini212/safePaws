package com.FinalProject.SafePaws.service;

import com.FinalProject.SafePaws.entity.Shelter;
import com.FinalProject.SafePaws.utils.dto.shelter.request.UpdateShelterDTO;

public interface ShelterService {

//    Shelter existsByEmail(String email);
    Shelter getByJWT();

    public Shelter updateShelter(UpdateShelterDTO request);
}
