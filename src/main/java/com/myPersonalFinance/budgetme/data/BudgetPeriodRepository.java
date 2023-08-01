package com.myPersonalFinance.budgetme.data;

import com.myPersonalFinance.budgetme.models.BudgetPeriod;
import com.myPersonalFinance.budgetme.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface BudgetPeriodRepository extends JpaRepository<BudgetPeriod, Integer> {

    }



