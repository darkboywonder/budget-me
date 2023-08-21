package com.myPersonalFinance.budgetme.models;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int expenseId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column
    @NotBlank(message = "Name is required")
    private String expenseName;

    @Column(precision=8, scale=2)
    private double amount;
    @Column
    String frequency;

    @Column
    private boolean isRepeated;
    @Column
    LocalDate dueDate;
    @Column
    private String category;


    public Expense(String expenseName, double amount, String frequency, boolean isRepeated, LocalDate dueDate, String category) {
        this.expenseName = expenseName;
        this.amount = amount;
        this.frequency = frequency;
        this.isRepeated = isRepeated;
        this.dueDate = dueDate;
        this.category = category;
    }

    public Expense() {}

    public int getExpenseId() {
        return expenseId;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setName(String expenseName) {
        this.expenseName = expenseName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount() {
        this.amount = Math.round(amount * 100) / 100.0;;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
        if (this.frequency == "") {
            this.frequency = "-";
        }
    }

    public boolean getIsRepeated() {
        return isRepeated;
    }

    public void setIsRepeated(boolean isRepeated) {
        this.isRepeated = isRepeated;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Expense)) return false;
        Expense expense = (Expense) o;
        return expenseId == expense.expenseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(expenseId);
    }

    public String toString() {

        if (this.category.equals("")){
            this.category = "-";
        }
        if (this.frequency.equals("")) {
            this.frequency = "-";
        }
        return "\n" + "ID: " + getExpenseId() + "\n" +
                "Name: " + getExpenseName() + "\n" +
                "Amount: " + getAmount() + "\n" +
                "Frequency: " + getFrequency() + "\n" +
                "Current due date: "  + getDueDate() + "\n" +
                "Repeating: " + getIsRepeated() + "\n" +
                "Category: " + getCategory() + "\n";
    }
}
