package com.example.spring_security_demo.service;

import com.example.spring_security_demo.dto.CategoryDTO;
import com.example.spring_security_demo.dto.CategoryHierarchyDTO;
import com.example.spring_security_demo.model.ProductCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductCategoryService {

    @Autowired
    private EntityManager entityManager;

    public CategoryHierarchyDTO getParentAndChildCategories() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = cb.createTupleQuery();
        Root<ProductCategory> parent = query.from(ProductCategory.class);
        Join<ProductCategory, ProductCategory> child = parent.join("children", JoinType.LEFT);

        query.multiselect(
                parent.get("id"),
                parent.get("name"),
                child.get("id"),
                child.get("name")
        );

        query.where(cb.isNull(parent.get("parent")));

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

    public CategoryDTO getChildCategories(int parentId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductCategory> query = cb.createQuery(ProductCategory.class);
        Root<ProductCategory> parent = query.from(ProductCategory.class);

        query.select(parent)
                .where(cb.equal(parent.get("id"), parentId));

        ProductCategory parentCategory = entityManager.createQuery(query).getSingleResult();

        List<CategoryDTO> children = parentCategory.getChildren().stream()
                .map(child -> new CategoryDTO(child.getId(), child.getName(), Optional.of(new ArrayList<>())))
                .collect(Collectors.toList());

        return new CategoryDTO(parentCategory.getId(), parentCategory.getName(), Optional.of(children));
    }

    public CategoryHierarchyDTO getParentCategories() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductCategory> query = cb.createQuery(ProductCategory.class);
        Root<ProductCategory> category = query.from(ProductCategory.class);

        query.select(category).where(cb.isNull(category.get("parent")));

        List<ProductCategory> parentCategories = entityManager.createQuery(query).getResultList();

        List<CategoryDTO> categoryDTOs = parentCategories.stream()
                .map(parent -> new CategoryDTO(parent.getId(), parent.getName(), Optional.of(new ArrayList<>())))
                .collect(Collectors.toList());

        return new CategoryHierarchyDTO(categoryDTOs);
    }
}


