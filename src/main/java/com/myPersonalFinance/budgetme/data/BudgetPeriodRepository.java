package com.myPersonalFinance.budgetme.data;

import com.myPersonalFinance.budgetme.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface BudgetPeriodRepository {

    @Repository
    public interface budgetPeriodRepository extends JpaRepository<User, Integer> {

    }

}

