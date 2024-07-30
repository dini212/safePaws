package com.FinalProject.SafePaws.utils.dto.security.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest {

    @NotBlank
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank
    private String password;
}
