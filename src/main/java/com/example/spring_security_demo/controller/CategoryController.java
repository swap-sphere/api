package com.example.spring_security_demo.controller;

import com.example.spring_security_demo.dto.CategoryDTO;
import com.example.spring_security_demo.dto.CategoryHierarchyDTO;

import com.example.spring_security_demo.dto.sql.ParentChildCategoryDTO;
import com.example.spring_security_demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/categories")
public class CategoryController {


//    @PostMapping("/category")
//    public ResponseEntity<String> addCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) throws BadRequestException {
//        categoryService.saveCategory(categoryRequestDTO.getName(), categoryRequestDTO.getParentId());
//////        return ResponseEntity.status(HttpStatus.CREATED).body("Category Created Successfully");
//
//        return new ResponseEntity<>("Success", HttpStatus.CREATED);
//    }
//
//    @GetMapping("/categories")
//    public CategoryHierarchyDTO getParentAndChildCategories() {
//        return productCategoryService.getParentAndChildCategories();
//    }
//
//    @GetMapping("/child-categories/{id}")
//    public CategoryDTO getChildCategories(@PathVariable int id){
//        return productCategoryService.getChildCategories(id);
//    }
//    @GetMapping("/parent-category")
//    public CategoryHierarchyDTO getParentCategory(){
//        return  productCategoryService.getParentCategories();
//
//    }

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/hierarchy")
    public ResponseEntity<CategoryHierarchyDTO> getCategoryHierarchy() {
        CategoryHierarchyDTO categoryHierarchyDTO = categoryService.getParentAndChildCategories();
        return new ResponseEntity<>(categoryHierarchyDTO, HttpStatus.OK);
    }
}


