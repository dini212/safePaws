package com.FinalProject.SafePaws.utils.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GenericIdRequest {
    @NotBlank
    private String id;
}
