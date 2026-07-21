package com.example.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {

    // Basic error information
    private String message;
    private String status;
    private int statusCode;


    // Validation errors
    private List<ValidationError> validationErrors;
    // Additional details
    private Map<String, Object> details;


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class ValidationError {
        private String field;
        private String rejectedValue;
        private String message;
    }
}
