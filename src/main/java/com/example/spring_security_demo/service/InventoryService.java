package com.example.spring_security_demo.service;

import com.example.spring_security_demo.dao.InventoryRepository;
import com.example.spring_security_demo.dto.InventoryFilterRequestDTO;
import com.example.spring_security_demo.dto.InventoryFilterResponseDTO;
import com.example.spring_security_demo.dto.sql.InventoryFilterQueryDTO;
import com.example.spring_security_demo.model.Category;
import com.example.spring_security_demo.model.FilterValues;
import com.example.spring_security_demo.model.Inventory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ObjectMapper objectMapper;
    public Inventory getInventories(int id) {
        Optional<Inventory> inventory = inventoryRepository.findById(id);
        return inventory.get();
    }

    public Map<String, Object> getInventoryDetails(Long inventoryId) {
        Map<String, Object> inventoryDetails = inventoryRepository.fetchInventoryDetails(inventoryId);

        if (inventoryDetails.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory not found");
        }

        Map<String, Object> mutableInventoryDetails = new HashMap<>(inventoryDetails);

        String specificationsJson = (String) mutableInventoryDetails.get("specifications");
        if (specificationsJson != null) {
            try {
                Map<String, Object> specifications = objectMapper.readValue(specificationsJson, Map.class);
                mutableInventoryDetails.put("specifications", specifications);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to parse specifications JSON", e);
            }
        } else {
            mutableInventoryDetails.put("specifications", Collections.emptyMap());

        }
        return mutableInventoryDetails;
    }

    public InventoryFilterResponseDTO getFilteredInventories(int categoryId, int page, int limit, List<Integer> filterOptionIds) {
        int offset = (page - 1) * limit;

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<InventoryFilterQueryDTO> cq = cb.createQuery(InventoryFilterQueryDTO.class);
        Root<Inventory> inventory = cq.from(Inventory.class);

        Join<Inventory, FilterValues> filterValue = inventory.join("filterValues");

        Join<Inventory, Category> category = inventory.join("categoryId");

        cq.select(cb.construct(
                InventoryFilterQueryDTO.class,
                inventory.get("id"),
                inventory.get("name"),
                inventory.get("description"),
                inventory.get("stock"),
                inventory.get("price"),
                inventory.get("discount"),
                category.get("name"),
                category.get("id")
        ));

        Predicate categoryPredicate = cb.equal(inventory.get("categoryId").get("id"), categoryId);  // Use categoryId instead of category
        if (!filterOptionIds.isEmpty()) {
            Predicate filterPredicate = filterValue.get("filterOptionId").get("id").in(filterOptionIds);
            cq.where(cb.and(categoryPredicate, filterPredicate));
        }

        cq.groupBy(
                inventory.get("id"),
                inventory.get("name"),
                inventory.get("description"),
                inventory.get("stock"),
                inventory.get("price"),
                inventory.get("discount"),
                category.get("name"),
                category.get("id")
        );

        TypedQuery<InventoryFilterQueryDTO> query = entityManager.createQuery(cq);
        query.setFirstResult(offset);
        query.setMaxResults(limit);

        long total = inventoryRepository.fetchFilteredInventoriesCount(categoryId, filterOptionIds);
        List<InventoryFilterQueryDTO> inventories = query.getResultList();
        return new InventoryFilterResponseDTO(inventories, limit, page, total);
    }
}
