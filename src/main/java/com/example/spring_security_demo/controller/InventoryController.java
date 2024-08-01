package com.example.spring_security_demo.controller;


import com.example.spring_security_demo.dto.GetInventoryResponseDTO;
import com.example.spring_security_demo.model.Inventory;
import com.example.spring_security_demo.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/inventories")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping("/{id}")
    public ResponseEntity<GetInventoryResponseDTO> getInventory(@PathVariable int id){
        Inventory inventory = inventoryService.getInventories(id);
        GetInventoryResponseDTO getInventoryResponseDTO = new GetInventoryResponseDTO(inventory.getName(),inventory.getDescription(),
                                                                                    inventory.getStock(),inventory.getDiscount(),inventory.getPrice(),inventory.getCategoryId().getId()) ;
        return new ResponseEntity<>(getInventoryResponseDTO, HttpStatus.OK);

    }
}
