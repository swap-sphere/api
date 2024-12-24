package com.example.spring_security_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Data
@AllArgsConstructor
public class ErrorResponseDTO {
    private int HttpStatus;
    private String message;
}
