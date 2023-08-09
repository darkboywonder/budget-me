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
    private double budgetAmount;

    public BudgetPeriod() {
    }
    public BudgetPeriod(String startDate, String endDate, double budgetAmount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.budgetAmount = budgetAmount;
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

    public double getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }
}