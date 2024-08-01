package com.example.spring_security_demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "filter_values")
public class FilterValues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "filter_option_id", referencedColumnName = "id")
    private FilterOption filter_option_id;

    @ManyToOne
    @JoinColumn(name = "inventory_id", referencedColumnName = "id")
    private Inventory inventory_id;

    public FilterValues(FilterOption filter_option_id,Inventory inventory_id){
        this.filter_option_id = filter_option_id;
        this.inventory_id = inventory_id;
    }
}
