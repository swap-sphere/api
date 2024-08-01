package com.example.spring_security_demo.dao;

import com.example.spring_security_demo.model.FilterValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilterValuesRepository extends JpaRepository<FilterValues,Integer> {
}
