package com.example.spring_security_demo.dto.sql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParentChildCategoryDTO {

    private String parentName;
    private String childName;
    private int id;
    private int parentId;

}
