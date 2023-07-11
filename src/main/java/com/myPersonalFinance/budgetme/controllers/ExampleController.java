package com.myPersonalFinance.budgetme.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/")
public class ExampleController {
    @GetMapping(path = "getAnswer")
    public ResponseEntity<?> getAnswer() {
        HashMap<String, String> response = new HashMap<>();
        response.put("data", "How are you doing?");
        return ResponseEntity.ok(response);
    }
}

