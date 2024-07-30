package com.FinalProject.SafePaws.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

import com.FinalProject.SafePaws.entity.*;
import com.FinalProject.SafePaws.repository.*;
import com.FinalProject.SafePaws.service.AdoptionService;
import com.FinalProject.SafePaws.service.AuthService;
import com.FinalProject.SafePaws.utils.GeneralSpecification;
import com.FinalProject.SafePaws.utils.dto.GenericIdRequest;
import com.FinalProject.SafePaws.utils.dto.adoption.AdoptionRequest;
import com.FinalProject.SafePaws.utils.dto.adoption.AdoptionResponse;
import com.FinalProject.SafePaws.utils.dto.adoption.UpdateAdoptionRequest;
import com.FinalProject.SafePaws.utils.dto.adoption.UserAdoptionResponse;
import com.FinalProject.SafePaws.utils.dto.animal.AnimalResponse;
import com.FinalProject.SafePaws.utils.dto.user.response.UserResponseDTO;
import com.FinalProject.SafePaws.utils.enums.AdoptionStatus;
import com.FinalProject.SafePaws.utils.enums.AnimalStatus;

@Service
@AllArgsConstructor
public class AdoptionServiceImpl implements AdoptionService {

    private final AdoptionRepository adoptionRepository;

    private final UserRepository userRepository;

    private final ShelterRepository shelterRepository;

    private final AnimalRepository animalRepository;

    private final AuthService authService;

    @Override
    public UserAdoptionResponse createAdoption(AdoptionRequest request) {
       UserDetails result = authService.getCurrentUser();

       User user = userRepository.findByEmail(result.getUsername())
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));

       Animal animal = animalRepository.findById(request.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Animal not found"));

       Shelter shelter = shelterRepository.findById(animal.getShelter().getId())
                .orElseThrow(() -> new RuntimeException("Shelter not found"));

       LocalDate inspectionDay;
       Optional<Adoption> latestAdoption = adoptionRepository.findFirstByOrderByInspectionDateDesc();
       if (latestAdoption.isPresent()) {
           inspectionDay = latestAdoption.get().getInspectionDate().plusDays(1);
       } else {
           inspectionDay = LocalDate.now().plusDays(1);
       }

       Adoption adoption = Adoption.builder()
               .animal(animal)
               .user(user)
               .adoptionStatus(AdoptionStatus.PENDING)
               .inspectionDate(inspectionDay)
               .build();

       adoptionRepository.saveAndFlush(adoption);

       AnimalResponse animalResponse = AnimalResponse.response(animal,shelter);

       return UserAdoptionResponse.response(adoption, animalResponse);

    }

    @Override
    public Page<AdoptionResponse> getAllByShelter(Pageable pageable, Adoption request) {

        UserDetails result = authService.getCurrentUser();

        Shelter shelter = shelterRepository.findByEmail(result.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));

        List<Animal> animals = animalRepository.findAllByShelterId(shelter.getId());

        List<String> animalIds = animals.stream().map(Animal::getId).toList();

        Specification<Adoption> specification = (root, query, criteriaBuilder) -> {
            Predicate animalPredicate = root.get("animal").get("id").in(animalIds);

            Specification<Adoption> additionalSpecification = GeneralSpecification.getSpecification(request);
            Predicate additionalConditions = additionalSpecification.toPredicate(root, query, criteriaBuilder);

            return criteriaBuilder.and(animalPredicate, additionalConditions);
        };

        Page<Adoption> adoptions = adoptionRepository.findAll(specification, pageable);

        List<AdoptionResponse> responseList = adoptions.stream()
                .map(adoption -> {
                    Animal animal = animalRepository.findById(adoption.getAnimal().getId())
                            .orElseThrow(() -> new RuntimeException("Animal not found"));

                    User user = userRepository.findById(adoption.getUser().getId())
                            .orElseThrow(() -> new RuntimeException("User not found"));

                    AnimalResponse animalResponse = AnimalResponse.response(animal, shelter);
                    UserResponseDTO userResponse = UserResponseDTO.fromUser(user);

                    return AdoptionResponse.response(adoption, animalResponse, userResponse);
                })
                .toList();

        return new PageImpl<>(responseList, pageable, adoptions.getTotalElements());
    }

    @Override
    public Page<UserAdoptionResponse> getAllByUser(Pageable pageable, Adoption request) {

        UserDetails result = authService.getCurrentUser();

        User user = userRepository.findByEmail(result.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));

        request.setUser(user);

        Specification<Adoption> specification = GeneralSpecification.getSpecification(request);
        Page<Adoption> adoptions = adoptionRepository.findAll(specification,pageable);

        List<UserAdoptionResponse> responseList = adoptions.stream()
                .map(adoption -> {
                    Animal animal = animalRepository.findById(adoption.getAnimal().getId())
                            .orElseThrow(() -> new RuntimeException("Animal not found"));

                    Shelter shelter = shelterRepository.findById(animal.getShelter().getId())
                            .orElseThrow(() -> new RuntimeException("Shelter not found"));

                    AnimalResponse animalResponse = AnimalResponse.response(animal,shelter);

                    return UserAdoptionResponse.response(adoption,animalResponse);
                })
                .toList();

        return new PageImpl<>(responseList,pageable,adoptions.getTotalElements());
    }

    @Override
    public AdoptionResponse updateAdoption(UpdateAdoptionRequest request) {
        Adoption adoption = adoptionRepository.findById(request.getAdoptionId())
                .orElseThrow(() -> new RuntimeException("Adoption not found"));

        Animal animal = animalRepository.findById(adoption.getAnimal().getId())
                .orElseThrow(() -> new RuntimeException("Animal not found"));

        UserDetails result = authService.getCurrentUser();

        Shelter shelter = shelterRepository.findByEmail(result.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));

        User user = userRepository.findById(adoption.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
                
        adoption.setAdoptionStatus(request.getAdoptionStatus());

        adoptionRepository.save(adoption);

        if (request.getAdoptionStatus() == AdoptionStatus.APPROVED) {
            List<Adoption> pendingAdoptions = adoptionRepository.findByAnimalIdAndAdoptionStatus(adoption.getAnimal().getId(), AdoptionStatus.PENDING);

            for(Adoption pendingAdoption : pendingAdoptions) {
                pendingAdoption.setAdoptionStatus(AdoptionStatus.REJECTED);
            }

            animal.setAnimalStatus(AnimalStatus.ADOPTED);
            animalRepository.save(animal);
            
        }

        return AdoptionResponse.response(adoption,AnimalResponse.response(animal,shelter),UserResponseDTO.fromUser(user));
    }

    @Override
    public void delete(GenericIdRequest request) {
        adoptionRepository.deleteById(request.getId());
    }
}
