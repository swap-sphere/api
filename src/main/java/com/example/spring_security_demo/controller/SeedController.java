package com.example.spring_security_demo.controller;

import com.example.spring_security_demo.dto.GenericResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seed")
public class SeedController {

    @GetMapping("")
    public ResponseEntity<GenericResponseDTO> seed(){
        GenericResponseDTO genericResponseDTO = new GenericResponseDTO("Success.");
        return new ResponseEntity<>(genericResponseDTO,HttpStatus.OK);
    }
}
