package com.myPersonalFinance.budgetme.models;

import com.myPersonalFinance.budgetme.controllers.BudgetPeriodController;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

    public BudgetPeriod() {
    }
    public BudgetPeriod(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
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
}