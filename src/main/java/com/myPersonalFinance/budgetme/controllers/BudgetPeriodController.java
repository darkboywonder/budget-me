package com.myPersonalFinance.budgetme.controllers;

import com.myPersonalFinance.budgetme.data.BudgetPeriodRepository;
import com.myPersonalFinance.budgetme.models.BudgetPeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/budget-period")
public class BudgetPeriodController {

    @Autowired
    private BudgetPeriodRepository budgetPeriodRepository;

    @PostMapping(path = "/create-budget-period", consumes = "application/json")
    public ResponseEntity<String> setBudgetPeriod(@RequestBody BudgetPeriod budgetPeriod) {
        try {
            budgetPeriodRepository.save(budgetPeriod);
            return ResponseEntity.ok("Budget period received and saved!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while saving!");
        }
    }
}






