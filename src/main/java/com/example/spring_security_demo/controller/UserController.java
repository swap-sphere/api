package com.example.spring_security_demo.controller;

import com.example.spring_security_demo.dto.LoginResponseDTO;
import com.example.spring_security_demo.dto.RegisterResponseDTO;
import com.example.spring_security_demo.model.User;
import com.example.spring_security_demo.service.JwtService;
import com.example.spring_security_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody User user){
        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        service.saveUser(user);
        RegisterResponseDTO responseDTO = new RegisterResponseDTO("User Registered Successfully.");
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody User user){
        Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));  //to authentiate the client side hardcoded username and password with db one ..!!

        if (authentication.isAuthenticated()){
            String jwtToken = jwtService.generateToken(user.getUsername());
            System.out.println(jwtService.generateToken(user.getUsername()));
            String username = user.getUsername();
            System.out.println(username);
            String name = user.getName();
            System.out.println(name);
            LoginResponseDTO.AccountDetails accountDetails = new LoginResponseDTO.AccountDetails(username,name);
            LoginResponseDTO responseDTO = new LoginResponseDTO(jwtToken,accountDetails);
            System.out.println(responseDTO);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            }
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
}
