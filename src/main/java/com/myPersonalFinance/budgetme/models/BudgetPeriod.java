package com.myPersonalFinance.budgetme.models;

import com.myPersonalFinance.budgetme.controllers.BudgetPeriodController;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BudgetPeriod {

    @Id //primary key
    @GeneratedValue //generate values for primary key
    private int id;

    @Column
    private String startDate;
    @Column
    private String endDate;

    @Column
    private double totalBudget;

    public BudgetPeriod() {
    }
    public BudgetPeriod(String startDate, String endDate, double totalBudget) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalBudget = totalBudget;

    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(double totalBudget) {
        this.totalBudget = totalBudget;
    }



}