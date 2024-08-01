package com.example.spring_security_demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inventories")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    private String description;

    private int stock;

    private float discount;

    private float price;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category categoryId;

    public Inventory(String name, String description, int stock, float price, float discount, Category categoryId) {
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.discount = discount;
        this.price = price;
        this.categoryId = categoryId;
    }
}
