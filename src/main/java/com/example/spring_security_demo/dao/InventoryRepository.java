package com.example.spring_security_demo.dao;

import com.example.spring_security_demo.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
}
