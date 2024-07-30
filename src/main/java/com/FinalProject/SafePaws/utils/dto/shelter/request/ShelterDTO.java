package com.FinalProject.SafePaws.utils.dto.shelter.request;

import com.FinalProject.SafePaws.entity.Shelter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShelterDTO {

    @NotBlank
    private String name;

    @NotBlank
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank
    private String password;

    private String phone;

    private String description;

    public Shelter toEntity() {
        return Shelter.builder()
                .name(name)
                .email(email)
                .password(password)
                .phone(phone)
                .description(description)
                .build();
    }
}