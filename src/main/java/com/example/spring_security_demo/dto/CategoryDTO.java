package com.example.spring_security_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class CategoryDTO {
//    private int id;
//    private String name;
//    private Optional<List<CategoryDTO>> children;
//
//}

@Data
@NoArgsConstructor

public class CategoryDTO {
    private int id;
    private String name;
    private Optional<List<CategoryDTO>> children;

    public CategoryDTO(int id, String name, Optional<List<CategoryDTO>> children) {
        this.id = id;
        this.name = name;
        this.children = children;
    }

    // Getters and setters
}