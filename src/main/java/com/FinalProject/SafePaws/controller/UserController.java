package com.FinalProject.SafePaws.controller;

import com.FinalProject.SafePaws.entity.Adoption;
import com.FinalProject.SafePaws.entity.Animal;
import com.FinalProject.SafePaws.entity.User;
import com.FinalProject.SafePaws.service.AddressUserService;
import com.FinalProject.SafePaws.service.AdoptionService;
import com.FinalProject.SafePaws.service.AnimalService;
import com.FinalProject.SafePaws.service.UserService;
import com.FinalProject.SafePaws.utils.dto.AddressResponse;
import com.FinalProject.SafePaws.utils.dto.GenericIdRequest;
import com.FinalProject.SafePaws.utils.dto.PageResponse;
import com.FinalProject.SafePaws.utils.dto.Res;
import com.FinalProject.SafePaws.utils.dto.adoption.AdoptionRequest;
import com.FinalProject.SafePaws.utils.dto.adoption.AdoptionResponse;
import com.FinalProject.SafePaws.utils.dto.adoption.UserAdoptionResponse;
import com.FinalProject.SafePaws.utils.dto.animal.AnimalRequest;
import com.FinalProject.SafePaws.utils.dto.animal.AnimalResponse;
import com.FinalProject.SafePaws.utils.dto.user.request.AddressUserDTO;
import com.FinalProject.SafePaws.utils.dto.user.request.UpdateAddressUserDTO;
import com.FinalProject.SafePaws.utils.dto.user.request.UpdateUserDTO;
import com.FinalProject.SafePaws.utils.dto.user.response.UserResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasRole('USER')")
@AllArgsConstructor
public class UserController {

    private final AddressUserService addressUserService;

    private final UserService userService;

    private final AnimalService animalService;

    private final AdoptionService adoptionService;

    @GetMapping
    public ResponseEntity<?> getCurrentUser() {
        User user = userService.getByJWT();
        return Res.renderJson(UserResponseDTO.fromUser(user), "Accepted", HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDTO request) {
        User user = userService.updateUser(request);
        return Res.renderJson(UserResponseDTO.fromUser(user), "OK", HttpStatus.OK);
    }

    @GetMapping(path = "/address")
    public ResponseEntity<?> getAddress(){

        List<AddressResponse> result = addressUserService.getAll();

        return Res.renderJson(result, "OK", HttpStatus.OK);
    }

    @PostMapping(path = "/address")
    public ResponseEntity<?> setAddress(@RequestBody AddressUserDTO request) throws JsonProcessingException {

        AddressResponse result = addressUserService.create(request);

        return Res.renderJson(result, "Address user added successfully", HttpStatus.CREATED);
    }

    @PutMapping(path = "/address")
    public ResponseEntity<?> putAddress(@RequestBody UpdateAddressUserDTO request) {

        AddressResponse result = addressUserService.update(request);

        return Res.renderJson(result, "Address user edited successfully", HttpStatus.OK);
    }

    @DeleteMapping(path = "/address")
    public ResponseEntity<?> deleteAnimal(@RequestBody GenericIdRequest request) {

        addressUserService.delete(request);

        return Res.renderJson(null, "Address user deleted successfully", HttpStatus.OK);
    }

    @GetMapping(path = "/animal")
    public ResponseEntity<?> getAnimal(
            @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable page,
            @ModelAttribute AnimalRequest request
    ) {

        PageResponse<AnimalResponse> responses = new PageResponse<>(animalService.getAllByUser(page, request));
        return Res.renderJson(responses, "OK", HttpStatus.OK);
    }

    @PostMapping(path = "/adoption")
    public ResponseEntity<?> createAdoption(@RequestBody AdoptionRequest request) {
        UserAdoptionResponse response = adoptionService.createAdoption(request);
        return Res.renderJson(response, "Data created", HttpStatus.CREATED);
    }

    @GetMapping(path = "/adoption")
    public ResponseEntity<?> getAdoption(
            @PageableDefault(page = 0, size = 10, sort = "inspectionDate", direction = Sort.Direction.ASC) Pageable page,
            @ModelAttribute Adoption request
    ) {

        PageResponse<UserAdoptionResponse> responses = new PageResponse<>(adoptionService.getAllByUser(page, request));
        return Res.renderJson(responses, "OK", HttpStatus.OK);
    }

    @DeleteMapping(path = "/adoption")
    public ResponseEntity<?> deleteAdoption(@RequestBody GenericIdRequest request){

        adoptionService.delete(request);

        return Res.renderJson(null, "Adoption Data Deleted", HttpStatus.OK);
    }
}
