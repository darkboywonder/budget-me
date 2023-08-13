package com.myPersonalFinance.budgetme.models;

import javax.persistence.*;


@Entity
@Table(name ="receipt")
public class Receipt {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generate values for primary key
    private int id;
    @Column
    private String title;

    @Column
    private double amount;
    @Column
    private String date;
    @Column
    private String tag;




    public Receipt(){};
    public Receipt(String title, String tag, double amount, String date) {
        this.title = title;
        this.tag = tag;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDate() { return date;}


    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
