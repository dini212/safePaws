package com.FinalProject.SafePaws.utils.dto.security.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {

    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }
}
