package com.example.spring_security_demo.dao;

import com.example.spring_security_demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Optional<Category> findById(Optional<Integer> parentId);

}
