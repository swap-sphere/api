package com.example.spring_security_demo.controller;


import com.example.spring_security_demo.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/inventories")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getInventoryDetails(@PathVariable("id") Long InventoryId){
        Map<String, Object> inventoryDetails = inventoryService.getInventoryDetails(InventoryId);
        return ResponseEntity.ok(inventoryDetails);

    }
}
