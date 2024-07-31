package com.example.spring_security_demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "filter_options")
public class FilterOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "filter_id", referencedColumnName = "id")
    private Filter filter_id;

    public FilterOption(String name,Filter filter_id){
        this.name = name;
        this.filter_id = filter_id;
    }
}
