package com.example.spring_security_demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String greet(){
        return "Hello world ";
    }
    @GetMapping("/about")
    public String about(HttpServletRequest request){
        return "this is the about page..!! " + request.getSession().getId();
    }
}
