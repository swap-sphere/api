package com.example.spring_security_demo.service;

import com.example.spring_security_demo.dto.CategoryDTO;
import com.example.spring_security_demo.dto.CategoryHierarchyDTO;
import com.example.spring_security_demo.model.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class CategoryService {

    @Autowired
    private EntityManager entityManager;

    public CategoryHierarchyDTO getParentAndChildCategories() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = cb.createTupleQuery();
        Root<Category> parent = query.from(Category.class);
        Join<Category, Category> child = parent.join("children", JoinType.LEFT);

        query.multiselect(
                parent.get("id"),
                parent.get("name"),
                child.get("id"),
                child.get("name")
        );

        query.where(cb.isNull(parent.get("parentId")));

        List<Tuple> results = entityManager.createQuery(query).getResultList();

        Map<Integer, CategoryDTO> categoryMap = new HashMap<>();

        for (Tuple tuple : results) {
            Integer parentId = tuple.get(0, Integer.class);
            String parentName = tuple.get(1, String.class);
            Integer childId = tuple.get(2, Integer.class);
            String childName = tuple.get(3, String.class);

            CategoryDTO parentCategory = categoryMap.computeIfAbsent(parentId, id -> new CategoryDTO(id, parentName, Optional.of(new ArrayList<>())));

            if (childId != null) {
                CategoryDTO childCategory = new CategoryDTO(childId, childName, Optional.of(new ArrayList<>()));
                parentCategory.getChildren().get().add(childCategory);
            }
        }

        List<CategoryDTO> topCategories = new ArrayList<>(categoryMap.values());
        return new CategoryHierarchyDTO(topCategories);
    }
}

