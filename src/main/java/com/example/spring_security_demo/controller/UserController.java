package com.example.spring_security_demo.controller;

import com.example.spring_security_demo.model.User;
import com.example.spring_security_demo.service.JwtService;
import com.example.spring_security_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @PostMapping("/register")
    public User register(@RequestBody User user){
        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        return service.saveUser(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody User user){
        Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));  //to authentiate the client side hardcoded username and password with db one ..!!

        if (authentication.isAuthenticated()){
            System.out.println(jwtService.generateToken(user.getUsername()));
            return jwtService.generateToken(user.getUsername()); }       //return the jwtoken based on the username once login is successful..
        else
            return "AUTHENTICATION FAILED...";
    }
}
