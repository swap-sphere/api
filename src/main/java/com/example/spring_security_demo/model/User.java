package com.example.spring_security_demo.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity   //since this class is an ORM entity
@Table(name = "users")  //since we have a different table name with the class name
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //auto generation of id by hibernite itself ,no need to pass id
    private int id;

    private String username;
    private String password;
    private String name;
}
