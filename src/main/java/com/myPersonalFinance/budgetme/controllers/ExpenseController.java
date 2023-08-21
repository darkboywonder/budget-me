package com.myPersonalFinance.budgetme.controllers;

import com.myPersonalFinance.budgetme.data.ExpenseRepository;
import com.myPersonalFinance.budgetme.data.UserRepository;
import com.myPersonalFinance.budgetme.models.Expense;
import com.myPersonalFinance.budgetme.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/create-expense", consumes = "application/json")
    public ResponseEntity<String> createExpense(@RequestBody Expense expense, @CookieValue(value = "sessionId") int sessionId) {
        try {
            User user = userRepository.findById(sessionId);
            expense.setUser(user);
            expenseRepository.save(expense);
            return ResponseEntity.ok("Expense created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during expense creation.");
        }
    }

    @GetMapping(path = "/expenses")
    public List<Expense> listExpenses(@CookieValue(value = "sessionId") int sessionId) {
        List<Expense> allExpenses = expenseRepository.findAll();
        List<Expense> userExpenses = new ArrayList<Expense>();
        for (Expense expense : allExpenses) {
            if (expense.getUser().getId() == sessionId) {
                userExpenses.add(expense);
            }
        }
        return userExpenses;
    }

    @PutMapping(path = "/update", consumes = "application/json")
    public ResponseEntity<String> updateExpense(@RequestBody Expense expense) {
        try {
            expenseRepository.save(expense);
            System.out.println(expense.getAmount());
            return ResponseEntity.ok("Expense created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during expense creation.");
        }
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> deleteExpense(@RequestBody int expenseId) {
        try {
            expenseRepository.deleteById(expenseId);
            return ResponseEntity.ok("Expense created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during expense creation.");
        }
    }
}


