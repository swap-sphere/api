package com.example.spring_security_demo.dao;

import com.example.spring_security_demo.model.FilterOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilterOptionRepository extends JpaRepository<FilterOption, Integer> {
}
