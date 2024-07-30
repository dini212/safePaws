package com.FinalProject.SafePaws.service;

import com.FinalProject.SafePaws.utils.dto.AddressResponse;
import com.FinalProject.SafePaws.utils.dto.GenericIdRequest;
import com.FinalProject.SafePaws.utils.dto.shelter.request.AddressShelterDTO;
import com.FinalProject.SafePaws.utils.dto.shelter.request.UpdateAddressShelterDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface AddressShelterService {

    AddressResponse create(AddressShelterDTO request) throws JsonProcessingException;
    List<AddressResponse> getAll();

    AddressResponse update(UpdateAddressShelterDTO request);

    void delete(GenericIdRequest request);
}
