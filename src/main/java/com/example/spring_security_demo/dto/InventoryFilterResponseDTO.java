package com.example.spring_security_demo.dto;

import com.example.spring_security_demo.dto.sql.InventoryFilterQueryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryFilterResponseDTO {
    private List<InventoryFilterQueryDTO> inventories;
    private int limit;
    private int page;
    private long total;

}
