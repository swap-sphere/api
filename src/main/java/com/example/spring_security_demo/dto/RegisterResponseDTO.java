package com.example.spring_security_demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterResponseDTO {
    @JsonProperty
    private String message;

    public RegisterResponseDTO(String message) {
        this.message = message;
    }
}
