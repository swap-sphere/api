package com.example.spring_security_demo.service;

import com.example.spring_security_demo.dao.CategoryRepository;
import com.example.spring_security_demo.model.Category;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public void saveCategory(String name, Optional<Integer> parentId) throws BadRequestException {
        Category category;

        if (parentId.isPresent()) {
            Category parentCategory = categoryRepository.findById(parentId.get())
                    .orElseThrow(() -> new BadRequestException("Provided parent category not found"));
            category = new Category(name, parentCategory);
        } else {
            category = new Category(name, null);
        }

        categoryRepository.save(category);
    }

}
