package com.example.spring_security_demo.controller;


import com.example.spring_security_demo.dto.InventoryFilterRequestDTO;
import com.example.spring_security_demo.dto.InventoryFilterResponseDTO;
import com.example.spring_security_demo.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/inventories")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getInventoryDetails(@PathVariable("id") Long InventoryId) {
        Map<String, Object> inventoryDetails = inventoryService.getInventoryDetails(InventoryId);
        return ResponseEntity.ok(inventoryDetails);

    }
    @PostMapping("/all")
    public ResponseEntity<InventoryFilterResponseDTO> fetchInventories(@RequestBody InventoryFilterRequestDTO inventoryFilterRequestDTO){
        InventoryFilterResponseDTO response = inventoryService.getFilteredInventories(
                inventoryFilterRequestDTO.getCategoryId(),
                inventoryFilterRequestDTO.getPage(),
                inventoryFilterRequestDTO.getLimit(),
                inventoryFilterRequestDTO.getFilterOptionIds()
        );
        return ResponseEntity.ok(response);
    }
}
