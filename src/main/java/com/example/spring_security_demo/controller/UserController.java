package com.example.spring_security_demo.controller;

import com.example.spring_security_demo.dto.*;
import com.example.spring_security_demo.model.User;
import com.example.spring_security_demo.model.UserPrincipal;
import com.example.spring_security_demo.service.JwtService;
import com.example.spring_security_demo.service.SeederService;
import com.example.spring_security_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    //to hold the userPrinciple object
    @Autowired
    private SeederService seederService;


    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody User user){
        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        service.saveUser(user);
        RegisterResponseDTO responseDTO = new RegisterResponseDTO("User Registered Successfully.");

        seederService.seed();

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO user){
        //creating authentication obj with requestDTO obj(LoginRequestDTO),ie client side request (user),
        Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));  //to authentiate the client side hardcoded username and password with db one ..!!

        System.out.println(authentication.getPrincipal());

        //checking the registered username and password is correct or not
        if (authentication.isAuthenticated()){

            UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
            //once authentication obj is created access the username from the authentication obj ,ie from the userPrincipal,not from the request ,ie ORM
            String jwtToken = jwtService.generateToken(userDetails.getUsername());
            System.out.println(jwtService.generateToken(userDetails.getUsername()));
            String username = userDetails.getUsername();
            System.out.println(username);
            String name = userDetails.getName();
            System.out.println(name);
            //nested class
            LoginResponseDTO.AccountDetails accountDetails = new LoginResponseDTO.AccountDetails(username,name);
            LoginResponseDTO responseDTO = new LoginResponseDTO(jwtToken,accountDetails);
            System.out.println(responseDTO);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            }
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping("/user")
    public ResponseEntity<UserResponseDTO> getUser(@RequestHeader("Authorization") String token){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();

        String username = userDetails.getUsername();
        String name = userDetails.getName();
        int id = userDetails.getId();
        UserResponseDTO userResponseDTO = new UserResponseDTO(username,name,id);

//        LoginRequestDTO responseDTO = new LoginRequestDTO(null)
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDTO);
    }

    @PostMapping("/seed")
    public ResponseEntity<GenericResponseDTO> seed() {
        return new ResponseEntity<>(new GenericResponseDTO("Success"), HttpStatus.CREATED);
    }
}
