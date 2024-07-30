package com.example.spring_security_demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenericResponseDTO {
    @JsonProperty
    private String message;

    public GenericResponseDTO(String message) {

        this.message = message;
    }
}
