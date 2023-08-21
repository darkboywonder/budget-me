package com.myPersonalFinance.budgetme.controllers;

import com.myPersonalFinance.budgetme.data.UserRepository;
import com.myPersonalFinance.budgetme.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private UserRepository userRepository;

    private static final String userSessionKey = "user";
    private HttpServletResponse response;


    private static void setUserInSession(HttpSession session, User user) {

        session.setAttribute(userSessionKey, user.getId());
    }


    @PostMapping(path = "/login", consumes = "application/json")
    public ResponseEntity<String> processLoginForm(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {

        User existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            // User is authenticated, create a session
            setUserInSession(request.getSession(), existingUser);
            //Creates ResponseEntity early to add a cookie to send to frontend
            ResponseEntity<String> responseEntity =
                    new ResponseEntity<String>("Login Successful", HttpStatus.OK);

            //Creates a cookie and adds it to the response
            Cookie userSessionCookie = new Cookie("sessionId", String.valueOf(existingUser.getId()));
            userSessionCookie.setMaxAge(-1);
            userSessionCookie.setPath("/");
            userSessionCookie.setSecure(true);
            userSessionCookie.setHttpOnly(true);
            response.addCookie(userSessionCookie);


            return responseEntity;

        } else {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during login");

        }
    }


    @PostMapping(path = "/register", consumes = "application/json")
    public ResponseEntity<String> processRegistrationForm(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {

         //Check if a user with the given username already exists
        User existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        try {
            // Save the new user to the database
            userRepository.save(user);
            // Create a user session
            setUserInSession(request.getSession(), user);
            ResponseEntity<String> responseEntity =
                    new ResponseEntity<>("Login Successful", HttpStatus.OK);

            //Creates a cookie
            Cookie userSessionCookie = new Cookie("sessionId", String.valueOf(user.getId()));
            userSessionCookie.setMaxAge(-1);
            userSessionCookie.setPath("/");
            userSessionCookie.setSecure(true);
            userSessionCookie.setHttpOnly(true);
            //Adds to the response
            response.addCookie(userSessionCookie);


            return responseEntity;






        } catch (Exception e) {
            // Handle any exceptions that occur during the save operation
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during registration");
        }
    }


    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username, @RequestBody User user) {

        userRepository.findByUsername(username);

        if (user != null) {
            return ResponseEntity.ok(user);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) {


        //Creates a cookie
        Cookie userSessionCookie = new Cookie("sessionId", "");
        userSessionCookie.setMaxAge(0);
        userSessionCookie.setPath("/");
        userSessionCookie.setSecure(true);
        userSessionCookie.setHttpOnly(true);
        //Adds to the http response
        response.addCookie(userSessionCookie);

        request.getSession().invalidate();
        return  ResponseEntity.ok("Logout Successful");
    }
}

