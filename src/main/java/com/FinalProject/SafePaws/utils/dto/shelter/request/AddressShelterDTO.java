package com.FinalProject.SafePaws.utils.dto.shelter.request;

import com.FinalProject.SafePaws.entity.*;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressShelterDTO {

    @NotBlank
    private String cityId;

    private String description;

    public AddressShelter toEntity(Shelter reqShelter, City reqCity) {
        return AddressShelter.builder()
                .shelter(reqShelter)
                .city(reqCity)
                .description(description)
                .build();
    }
}
