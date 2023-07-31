package com.myPersonalFinance.budgetme.controllers;

import com.myPersonalFinance.budgetme.data.BudgetPeriodRepository;
import com.myPersonalFinance.budgetme.data.UserRepository;
import com.myPersonalFinance.budgetme.models.BudgetPeriod;
import com.myPersonalFinance.budgetme.models.dto.LoginFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budget-period")
    public class BudgetPeriodController {

        @Autowired
        private BudgetPeriodRepository budgetPeriodRepository;



    @PostMapping("/create")
        
}







