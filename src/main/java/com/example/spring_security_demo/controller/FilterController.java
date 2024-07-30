package com.example.spring_security_demo.controller;
import com.example.spring_security_demo.dto.CreateFilterRequestDTO;
import com.example.spring_security_demo.dto.GenericResponseDTO;
import com.example.spring_security_demo.model.Filter;
import com.example.spring_security_demo.service.FilterService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilterController {

    @Autowired
    FilterService filterService;


    @PostMapping("/filter")
    public ResponseEntity<GenericResponseDTO> createFilter(@RequestBody CreateFilterRequestDTO createFilterRequestDTO) throws BadRequestException {
        Filter filter = filterService.saveFilter(createFilterRequestDTO.getCategoryId(), createFilterRequestDTO.getFilterName());
        filterService.saveFilterOptions(createFilterRequestDTO.getFilterOptions(), filter);
        GenericResponseDTO genericResponseDTO = new GenericResponseDTO("Successfully created");
        return new ResponseEntity<>(genericResponseDTO, HttpStatus.CREATED);
    }

}