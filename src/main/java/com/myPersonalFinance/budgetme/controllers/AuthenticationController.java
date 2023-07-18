package com.myPersonalFinance.budgetme.controllers;

import com.myPersonalFinance.budgetme.models.User;
import com.myPersonalFinance.budgetme.data.UserRepository;
import com.myPersonalFinance.budgetme.models.dto.LoginFormDTO;
import com.myPersonalFinance.budgetme.models.dto.RegisterFormDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {

        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }
        return user.get();
    }

    private static void setUserInSession(HttpSession session, User user) {

        session.setAttribute(userSessionKey, user.getId());
    }

    @GetMapping("/register")
    public String displayRegistrationForm(Model model) {

        model.addAttribute(new RegisterFormDTO());
        model.addAttribute("title", "Register");

        return "register";
    }
    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute @Valid RegisterFormDTO registerFormDTO,
                                          Errors errors, HttpServletRequest request,
                                          Model model) {

        if (errors.hasErrors()) { //return the user to the form if any validation errors occur
            model.addAttribute("title", "Register");
            return "register";
        }

        User existingUser = userRepository.findByUsername(registerFormDTO.getUsername());
        //retrieve the user with the given username from the database

        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            model.addAttribute("title", "Register");
            return "register";
        }

        //compare the two passwords submitted. If they do not match, register a custom error and return the user to the form
        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "register";

            //errors.rejectValue takes 3 parameters:
            //1. the field containing the error
            //2. a label representing the error. This allows error messages to be imported from another file. Even if we don't have such a file, this parameter is required.
            //3. A default message to use if no external error message file is available

        }

        //If all is valid and a user does not exist, create a new user object, store it in the database, and then create a new session for the user
        User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getPassword());
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);

        return "redirect:"; //redirect to the home page

    }
    @GetMapping("/login")
    public String displayLoginForm(Model model) {
        model.addAttribute(new LoginFormDTO());
        model.addAttribute("title", "Log In");
        return "login";
    }
    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                                   Errors errors, HttpServletRequest request,
                                   Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Log In");
            return "login";
        }

        User theUser = userRepository.findByUsername(loginFormDTO.getUsername());

        if (theUser == null) {
            errors.rejectValue("username", "user.invalid", "The username does not exist. Please register for an account.");
            model.addAttribute("title", "Log In");
            return "login";
        }

        String password = loginFormDTO.getPassword();

        if (!theUser.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            model.addAttribute("title", "Log In");
            return "login";
        }

        setUserInSession(request.getSession(), theUser);

        return "redirect:";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }

}

//@RestController
//@RequestMapping("/api/auth")
//public class AuthenticationController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginFormDTO loginFormDTO) {
//        User user = userRepository.findByUsername(loginFormDTO.getUsername());
//
//        if (user == null || !user.isMatchingPassword(loginFormDTO.getPassword())) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//        }
//
//        // Perform successful login logic, such as generating and returning an authentication token
//        String authToken = generateAuthToken();
//        return ResponseEntity.ok(authToken);
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody RegisterFormDTO registerFormDTO) {
//        if (userRepository.findByUsername(registerFormDTO.getUsername()) != null) {
//            return ResponseEntity.badRequest().body("A user with that username already exists");
//        }
//
//        // Perform successful registration logic, such as creating a new user and saving it to the repository
//        User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getPassword());
//        userRepository.save(newUser);
//
//        // Optionally, you can automatically log in the user after registration
//        // For example, generate and return an authentication token
//        String authToken = generateAuthToken();
//        return ResponseEntity.ok(authToken);
//    }
//
//    // Other authentication-related endpoints and methods
//
//    private String generateAuthToken() {
//        // Logic to generate a secure authentication token
//        return "dummyAuthToken";
//    }
//
//}
