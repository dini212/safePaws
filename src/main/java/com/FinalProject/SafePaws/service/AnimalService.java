package com.FinalProject.SafePaws.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.FinalProject.SafePaws.utils.dto.GenericIdRequest;
import com.FinalProject.SafePaws.utils.dto.animal.AnimalRequest;
import com.FinalProject.SafePaws.utils.dto.animal.AnimalResponse;
import com.FinalProject.SafePaws.utils.dto.animal.AnimalUpdateRequest;
import com.FinalProject.SafePaws.utils.dto.animal.GetAnimalRequest;

public interface AnimalService {
    AnimalResponse create(AnimalRequest request);
    Page<AnimalResponse> getAllByShelter(Pageable pageable, GetAnimalRequest request);
    Page<AnimalResponse> getAllByUser(Pageable pageable, AnimalRequest request);
    AnimalResponse getById(GenericIdRequest request);
    AnimalResponse update(AnimalUpdateRequest request);
    void delete(GenericIdRequest request);
}
