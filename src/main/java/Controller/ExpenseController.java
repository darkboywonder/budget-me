package com.myPersonalFinance.budgetme.MVC.controllers;

import data.ExpenseRepository;
import model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@SpringBootApplication
@RequestMapping("/expense")
public class ExpenseController {

@Autowired
    private ExpenseRepository expenseRepository;


@GetMapping("/create")
    public String displayCreateExpenseForm(Model model) {
    model.addAttribute("title", "Create Expense");
    model.addAttribute(new Expense());
    return "expense/create";
}

@PostMapping("/create")
    public String processCreateExpenseForm(@ModelAttribute @Valid Expense newExpense, Errors errors, Model model) {
     if(errors.hasErrors()) {
         model.addAttribute("title", "Create Expense");
         return "expense/create";
     }
     expenseRepository.save(newExpense);
     return "redirect:";
}
}
