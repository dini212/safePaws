package com.FinalProject.SafePaws.utils.dto.adoption;

import lombok.*;

import java.time.LocalDate;

import com.FinalProject.SafePaws.entity.Adoption;
import com.FinalProject.SafePaws.utils.dto.animal.AnimalResponse;
import com.FinalProject.SafePaws.utils.dto.user.response.UserResponseDTO;
import com.FinalProject.SafePaws.utils.enums.AdoptionStatus;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdoptionResponse {

    private String id;
    private AdoptionStatus adoptionStatus;
    private AnimalResponse animal;
    private UserResponseDTO user;
    private LocalDate inspectionDay;

    public static AdoptionResponse response(Adoption adoption, AnimalResponse animalResponse, UserResponseDTO userResponse) {
        return AdoptionResponse.builder()
                .id(adoption.getId())
                .adoptionStatus(adoption.getAdoptionStatus())
                .animal(animalResponse)
                .user(userResponse)
                .inspectionDay(adoption.getInspectionDate())
                .build();
    }

}
