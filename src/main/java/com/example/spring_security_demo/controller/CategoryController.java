package com.example.spring_security_demo.controller;

import com.example.spring_security_demo.dto.CategoryRequestDTO;
import com.example.spring_security_demo.model.Category;
import com.example.spring_security_demo.service.CategoryService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<String> addCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) throws BadRequestException {
        categoryService.saveCategory(categoryRequestDTO.getName(),categoryRequestDTO.getParentId());
//        Category savedCategory = categoryService.saveCategory(categoryRequestDTO);
////        return ResponseEntity.status(HttpStatus.CREATED).body("Category Created Successfully");
//        return new ResponseEntity<>("Category Created Successfully",HttpStatus.CREATED);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
}

