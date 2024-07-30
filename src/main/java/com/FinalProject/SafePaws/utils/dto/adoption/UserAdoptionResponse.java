package com.FinalProject.SafePaws.utils.dto.adoption;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.FinalProject.SafePaws.entity.Adoption;
import com.FinalProject.SafePaws.utils.dto.animal.AnimalResponse;
import com.FinalProject.SafePaws.utils.enums.AdoptionStatus;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAdoptionResponse {

    private String id;
    private AdoptionStatus adoptionStatus;
    private AnimalResponse animal;
    private LocalDate inspectionDay;

    public static UserAdoptionResponse response(Adoption adoption, AnimalResponse animalResponse) {
        return UserAdoptionResponse.builder()
                .id(adoption.getId())
                .adoptionStatus(adoption.getAdoptionStatus())
                .animal(animalResponse)
                .inspectionDay(adoption.getInspectionDate())
                .build();
    }

}
