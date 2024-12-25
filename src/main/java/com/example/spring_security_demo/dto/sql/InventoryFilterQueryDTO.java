package com.example.spring_security_demo.dto.sql;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InventoryFilterQueryDTO {
        private Integer inventoryId;
        private String productName;
        private String description;
        private Integer stock;
        private Float price;
        private Float discount;
        private String categoryName;
        private Integer categoryId;
}
