package com.FinalProject.SafePaws.utils.dto.user.request;

import com.FinalProject.SafePaws.entity.User;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateUserDTO {
    @NotBlank
    private String id;

    @NotBlank
    private String fullName;

    @NotBlank
    private String password;

    private String phone;

    public User toEntity(){
        return User.builder()
                .id(id)
                .fullName(fullName)
                .password(password)
                .phone(phone)
                .build();
    }
}
