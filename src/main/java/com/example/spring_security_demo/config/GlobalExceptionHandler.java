package com.example.spring_security_demo.config;

import com.example.spring_security_demo.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<ErrorResponseDTO> handleResponseStatusException(ResponseStatusException exception){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(exception.getStatusCode().value(), exception.getReason());
        return ResponseEntity.status(exception.getStatusCode().value()).body(errorResponseDTO);
    }
}
