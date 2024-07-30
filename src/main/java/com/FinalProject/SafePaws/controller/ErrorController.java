package com.FinalProject.SafePaws.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.FinalProject.SafePaws.utils.dto.WebResponse;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<WebResponse<String>> apiException(ResponseStatusException e) {
        return ResponseEntity.status(e.getStatusCode())
                .body(WebResponse.<String>builder().message("ERROR: "+e.getReason()).build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
