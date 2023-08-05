package com.myPersonalFinance.budgetme.models;

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
    private String month;
    @Column
    private int year;

    public BudgetPeriod() {
    }

    public BudgetPeriod(String month, int year) {
        this.month = month;
        this.year = year;

    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}

