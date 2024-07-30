package com.FinalProject.SafePaws.utils.dto.shelter.response;

import com.FinalProject.SafePaws.entity.*;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShelterResponseDTO {

    private String id;

    private String name;

    private String email;

    private String phone;

    private String description;

    public static ShelterResponseDTO fromUser(Shelter shelter){
        return ShelterResponseDTO.builder()
                .id(shelter.getId())
                .name(shelter.getName())
                .email(shelter.getEmail())
                .phone(shelter.getPhone())
                .description(shelter.getDescription())
                .build();
    }
}
