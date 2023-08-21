package com.myPersonalFinance.budgetme.models;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class ActualTransaction {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column
    private double amount;
    @Column
    private LocalDate date;
    @Column
    private String merchantName;
    @Column
    private String category;
    public ActualTransaction(){};

    public ActualTransaction(User user, double amount, LocalDate date, String merchantName, String category) {
        this.user = user;
        this.amount = amount;
        this.date = date;
        this.merchantName = merchantName;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getCategory() {
        return category;
    }
}
