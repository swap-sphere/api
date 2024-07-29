package com.example.spring_security_demo.controller;

import com.example.spring_security_demo.dto.CategoryDTO;
import com.example.spring_security_demo.dto.CategoryHierarchyDTO;
import com.example.spring_security_demo.dto.CategoryRequestDTO;
import com.example.spring_security_demo.service.CategoryService;
import com.example.spring_security_demo.service.ProductCategoryService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping("/category")
    public ResponseEntity<String> addCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) throws BadRequestException {
        categoryService.saveCategory(categoryRequestDTO.getName(), categoryRequestDTO.getParentId());
////        return ResponseEntity.status(HttpStatus.CREATED).body("Category Created Successfully");

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @GetMapping("/categories")
    public CategoryHierarchyDTO getParentAndChildCategories() {
        return productCategoryService.getParentAndChildCategories();
    }

    @GetMapping("/child-categories/{id}")
    public CategoryDTO getChildCategories(@PathVariable int id){
        return productCategoryService.getChildCategories(id);
    }
    @GetMapping("/parent-category")
    public CategoryHierarchyDTO getParentCategory(){
        return  productCategoryService.getParentCategories();

    }
}


