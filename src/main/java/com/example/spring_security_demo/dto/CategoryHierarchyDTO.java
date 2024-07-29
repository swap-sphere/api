package com.example.spring_security_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CategoryHierarchyDTO {
    private List<CategoryDTO> categories;

    public CategoryHierarchyDTO(List<CategoryDTO> categories) {
        this.categories = categories;
    }

}