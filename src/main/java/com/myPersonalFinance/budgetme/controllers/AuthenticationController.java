package com.myPersonalFinance.budgetme.controllers;

import com.myPersonalFinance.budgetme.data.UserRepository;
import com.myPersonalFinance.budgetme.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private UserRepository userRepository;

    private static final String userSessionKey = "user";


    private static void setUserInSession(HttpSession session, User user) {

        session.setAttribute(userSessionKey, user.getId());
    }


    @PostMapping(path = "/login", consumes = "application/json")
    public ResponseEntity<String> processLoginForm(@RequestBody User user, HttpServletRequest request) {

        User existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            // User is authenticated, create a session
            setUserInSession(request.getSession(), existingUser);

            return ResponseEntity.ok("Login successful");

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
            return ResponseEntity.ok("Registration successful");

        } catch (Exception e) {
            // Handle any exceptions that occur during the save operation
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during registration");
        }
    }

}

