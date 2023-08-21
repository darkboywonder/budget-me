package com.myPersonalFinance.budgetme.data;

import com.myPersonalFinance.budgetme.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
}



