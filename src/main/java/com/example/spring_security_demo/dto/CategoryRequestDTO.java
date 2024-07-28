package com.example.spring_security_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

@Data
@AllArgsConstructor
public class CategoryRequestDTO {
    private String name;

    private Optional<Integer> parentId;
}
