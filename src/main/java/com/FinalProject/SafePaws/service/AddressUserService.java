package com.FinalProject.SafePaws.service;

import com.FinalProject.SafePaws.utils.dto.AddressResponse;
import com.FinalProject.SafePaws.utils.dto.GenericIdRequest;
import com.FinalProject.SafePaws.utils.dto.user.request.AddressUserDTO;
import com.FinalProject.SafePaws.utils.dto.user.request.UpdateAddressUserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface AddressUserService {
    AddressResponse create(AddressUserDTO request) throws JsonProcessingException ;

    List<AddressResponse> getAll();

    AddressResponse update(UpdateAddressUserDTO request);
    void delete(GenericIdRequest request);

}
