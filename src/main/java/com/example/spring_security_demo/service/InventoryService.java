package com.example.spring_security_demo.service;

import com.example.spring_security_demo.dao.InventoryRepository;
import com.example.spring_security_demo.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;
    public Inventory getInventories(int id){
        Optional<Inventory> inventory = inventoryRepository.findById(id);
        return inventory.get();
    }
}
