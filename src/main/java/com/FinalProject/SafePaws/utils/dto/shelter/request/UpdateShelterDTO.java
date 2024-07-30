package com.FinalProject.SafePaws.utils.dto.shelter.request;

import com.FinalProject.SafePaws.entity.Shelter;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateShelterDTO {
    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    private String phone;

    private String description;

    public Shelter toEntity() {
        return Shelter.builder()
                .id(id)
                .name(name)
                .password(password)
                .phone(phone)
                .description(description)
                .build();
    }
}
