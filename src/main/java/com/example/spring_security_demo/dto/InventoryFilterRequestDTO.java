package com.example.spring_security_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryFilterRequestDTO {
    private int page;
    private int limit;
    private List<Integer> filterOptionIds;
    private int categoryId;
}
