package com.FinalProject.SafePaws.utils.dto.user.request;

import com.FinalProject.SafePaws.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {

    @NotBlank
    private String fullName;

    @NotBlank
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank
    private String password;

    private String phone;

    public User toEntity() {
        return User.builder()
                .fullName(fullName)
                .email(email)
                .password(password)
                .phone(phone)
                .build();
    }
}
