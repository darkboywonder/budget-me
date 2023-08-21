package com.myPersonalFinance.budgetme.controllers;

import com.myPersonalFinance.budgetme.data.TransactionsRepository;
import com.myPersonalFinance.budgetme.models.ActualTransaction;
import com.myPersonalFinance.budgetme.models.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionsRepository transactionsRepository;
    @GetMapping(path = "/getTransactions")
    public List<ActualTransaction> listExpenses(@CookieValue(value = "sessionId")int sessionId) {
        return transactionsRepository.findByUserId(sessionId);

    }

}
