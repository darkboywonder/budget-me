package com.myPersonalFinance.budgetme.data;

import com.myPersonalFinance.budgetme.models.BudgetPeriod;
import com.myPersonalFinance.budgetme.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

    @Repository
    public interface BudgetPeriodRepository extends JpaRepository<BudgetPeriod, Integer> {
        BudgetPeriod findById(int id);

        @Modifying
        @Query("UPDATE BudgetPeriod b SET b.budgetAmount = :budgetAmount WHERE b.id = :id")
        int updateBudgetAmountById(@Param("id") int id, @Param("budgetAmount") double budgetAmount);
    };






