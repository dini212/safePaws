package com.FinalProject.SafePaws.utils.dto.shelter.request;

import com.FinalProject.SafePaws.entity.AddressUser;
import com.FinalProject.SafePaws.entity.City;
import com.FinalProject.SafePaws.entity.User;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateAddressShelterDTO {
    @NotBlank
    private String id;

    @NotBlank
    private String cityId;

    private String description;

    public AddressUser toEntity(User reqUser, City reqCity) {
        return AddressUser.builder()
                .user(reqUser)
                .city(reqCity)
                .description(description)
                .build();
    }
}
