package com.example.spring_security_demo.dao;

import com.example.spring_security_demo.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    @Query(value = """
            SELECT 
                i.id AS inventory_id,
                i.name AS inventory_name,
                i.description,
                i.stock,
                i.price,
                i.discount,
                c.name AS category_name,
                jsonb_object_agg(f.name, fo.name) AS specifications
            FROM 
                inventories i
            JOIN 
                categories c ON i.category_id = c.id
            JOIN 
                filter_values fv ON i.id = fv.inventory_id
            JOIN 
                filter_options fo ON fv.filter_option_id = fo.id
            JOIN 
                filters f ON fo.filter_id = f.id
            WHERE 
                i.id = :inventoryId
            GROUP BY 
                i.id, i.name, i.description, i.stock, i.price, i.discount, c.name
            """, nativeQuery = true)
    Map<String, Object> fetchInventoryDetails(@Param("inventoryId") Long inventoryId);

}
