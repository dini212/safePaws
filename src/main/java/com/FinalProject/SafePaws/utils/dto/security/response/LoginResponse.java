package com.FinalProject.SafePaws.utils.dto.security.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private String access_token;

}

