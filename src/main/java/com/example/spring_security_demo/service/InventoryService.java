package com.example.spring_security_demo.service;

import com.example.spring_security_demo.dao.InventoryRepository;
import com.example.spring_security_demo.model.Inventory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class InventoryService {

    private static final Logger logger = LoggerFactory.getLogger(InventoryService.class);

    private final InventoryRepository inventoryRepository;
    private final ObjectMapper objectMapper;

    public InventoryService(InventoryRepository inventoryRepository, ObjectMapper objectMapper) {
        this.inventoryRepository = inventoryRepository;
        this.objectMapper = objectMapper;
    }

    public Inventory getInventoryById(int id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory not found"));
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
}
