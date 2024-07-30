package com.example.spring_security_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateFilterRequestDTO {
    private String filterName;
    private int categoryId;
    private List<String> filterOptions;
}
