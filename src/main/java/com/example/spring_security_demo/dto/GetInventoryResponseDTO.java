package com.example.spring_security_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetInventoryResponseDTO {
    private String name;
    private String description;
    private int stock;
    private float discount;
    private float price;
    private int categoryId;
}
