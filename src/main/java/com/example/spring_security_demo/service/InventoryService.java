package com.example.spring_security_demo.service;

import com.example.spring_security_demo.dao.InventoryRepository;
import com.example.spring_security_demo.model.Inventory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    private ObjectMapper objectMapper;
    public Inventory getInventories(int id) {
        Optional<Inventory> inventory = inventoryRepository.findById(id);
        return inventory.get();
    }

    public Map<String, Object> getInventoryDetails(Long inventoryId) {
        Map<String, Object> inventoryDetails = inventoryRepository.fetchInventoryDetails(inventoryId);

        if (inventoryDetails == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory not found");
        }

        Map<String, Object> mutableInventoryDetails = new HashMap<>(inventoryDetails);

        String specificationsJson = (String) mutableInventoryDetails.get("specifications");
        try {
            Map<String, Object> specifications = objectMapper.readValue(specificationsJson, Map.class);
            mutableInventoryDetails.put("specifications", specifications);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse specifications JSON", e);
        }
        return mutableInventoryDetails;
    }
}
