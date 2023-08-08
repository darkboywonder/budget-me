package com.myPersonalFinance.budgetme.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="receipt")
public class Receipt {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generate values for primary key
    private int id;
    @Column
    private String title;

    @Column
    private int amount;
    @Column
    private String date;
    @Column
    private String tag;




    public Receipt(){};
    public Receipt(String title, String tag, int amount, String date) {
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
