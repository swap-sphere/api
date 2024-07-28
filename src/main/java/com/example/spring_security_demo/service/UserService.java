package com.example.spring_security_demo.service;

import com.example.spring_security_demo.dao.UserRepository;
import com.example.spring_security_demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepository repo;
    public User saveUser(User user){
        return repo.save(user);
    }
}
