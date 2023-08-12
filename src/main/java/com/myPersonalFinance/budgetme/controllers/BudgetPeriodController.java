package com.myPersonalFinance.budgetme.controllers;

import com.myPersonalFinance.budgetme.data.BudgetPeriodRepository;
import com.myPersonalFinance.budgetme.models.BudgetPeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/budget-period-list")
public class BudgetPeriodController {

    @Autowired
    private BudgetPeriodRepository budgetPeriodRepository;

    @PostMapping(path="/create-budget-period", consumes="application/json")
    public ResponseEntity<String> setBudgetPeriod(@RequestBody BudgetPeriod budgetPeriod) {
        System.out.println(budgetPeriod);
        try {
            budgetPeriodRepository.save(budgetPeriod);

            return ResponseEntity.ok("Budget period received and saved!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while saving!");
        }
    }
//    @PostMapping(path = "/add-budget-amount", consumes = "application/json")
//    public ResponseEntity<String> addBudgetAmount(@RequestBody BudgetAmountRequest request) {
//        try {
//            BudgetPeriod budgetPeriod = budgetPeriodRepository.findById(request.getBudgetPeriodId(int id)).orElse(null);
//            if (budgetPeriod != null) {
//                budgetPeriod.setBudgetAmount(request.getBudgetAmount());
//                budgetPeriodRepository.save(budgetPeriod);
//                return ResponseEntity.ok("Budget amount added successfully!");
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding budget amount!");
//        }
//    }
//}

    @GetMapping("/{id}")
    public ResponseEntity<BudgetPeriod> getBudgetById(@PathVariable int id, @RequestBody BudgetPeriod budgetPeriod) {
        budgetPeriodRepository.findById(id);

        if (budgetPeriod != null) {
            return ResponseEntity.ok(budgetPeriod);
        } else {
            return ResponseEntity.notFound().build();


        }

    }
}