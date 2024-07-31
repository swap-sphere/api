package com.example.spring_security_demo.dao;
import com.example.spring_security_demo.dto.sql.ParentChildCategoryDTO;
import com.example.spring_security_demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Optional<Category> findById(Optional<Integer> parentId);

//    @Query("SELECT new com.example.spring_security_demo.dto.sql.ParentChildCategoryDTO(parent.name, child.name, child.id, parent.id) " +
//            "FROM Category parent LEFT JOIN Category child ON parent.id = child.parentId " +
//            "WHERE parent.parentId IS NULL")
//    List<ParentChildCategoryDTO> findCategoryHierarchy();






}
