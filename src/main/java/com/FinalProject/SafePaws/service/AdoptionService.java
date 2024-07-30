package com.FinalProject.SafePaws.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.FinalProject.SafePaws.entity.Adoption;
import com.FinalProject.SafePaws.utils.dto.GenericIdRequest;
import com.FinalProject.SafePaws.utils.dto.adoption.AdoptionRequest;
import com.FinalProject.SafePaws.utils.dto.adoption.AdoptionResponse;
import com.FinalProject.SafePaws.utils.dto.adoption.UpdateAdoptionRequest;
import com.FinalProject.SafePaws.utils.dto.adoption.UserAdoptionResponse;

public interface AdoptionService {
    UserAdoptionResponse createAdoption(AdoptionRequest request);
    Page<AdoptionResponse> getAllByShelter(Pageable pageable, Adoption request);
    Page<UserAdoptionResponse> getAllByUser(Pageable pageable, Adoption request);
    AdoptionResponse updateAdoption(UpdateAdoptionRequest request);
    void delete(GenericIdRequest request);

}
