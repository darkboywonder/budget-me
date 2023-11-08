package com.myPersonalFinance.budgetme.controllers;

import com.myPersonalFinance.budgetme.data.UserRepository;
import com.myPersonalFinance.budgetme.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private UserRepository userRepository;


    @PostMapping(path = "/login", consumes = "application/json")
    public ResponseEntity<String> processLoginForm(@RequestBody User user) {

        User existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return new ResponseEntity<String>("Login Successful", HttpStatus.OK);

        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during login");

        }
    }


    @PostMapping(path = "/register", consumes = "application/json")
    public ResponseEntity<String> processRegistrationForm(@RequestBody User user) {

        //Check if a user with the given username already exists
        User existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        try {
            // Save the new user to the database
            userRepository.save(user);
            return new ResponseEntity<>("Login Successful", HttpStatus.OK);

        } catch (Exception e) {
            // Handle any exceptions that occur during the save operation
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during registration");
        }
    }
}